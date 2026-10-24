package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.RoleEntity;
import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.mapper.RoleMapper;
import edu.eci.dosw.TechCup.mapper.UserMapper;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.repository.RoleRepository;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.exception.AuthenticationException;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Profile("prod")
public class UserServiceProd implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger log =
            LoggerFactory.getLogger(UserServiceProd.class);
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public UserServiceProd(
            UserRepository userRepository,
            UserMapper userMapper, RoleMapper roleMapper, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Optional<User> searchUserByEmail(String mail) {

        log.info("Buscando usuario por email: {}", mail);

        return userRepository.findByEmail(mail)
                .map(userMapper::toModel);
    }

    @Transactional
    public Optional<User> searchUserById(Long id) {

        log.info("Buscando usuario por id: {}", id);

        return userRepository.findById(id)
                .map(userMapper::toModel);
    }

    @Transactional
    public Optional<User> searchUserByIdentification(String identification) {

        log.info("Buscando usuario por identificación: {}", identification);

        return userRepository.findByIdentification(identification)
                .map(userMapper::toModel);
    }

    @Transactional
    public User addRole(Long id, Long roleId) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userEntity.getRoles().add(roleEntity);

        UserEntity newUser = userRepository.save(userEntity);

        return userMapper.toModel(newUser);
    }

    @Transactional
    public User addRole(Long id, String roleName) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity roleEntity = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userEntity.getRoles().add(roleEntity);

        UserEntity newUser = userRepository.save(userEntity);

        return userMapper.toModel(newUser);
    }

    @Override
    public void deleteRole(Long id, Long roleId) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userEntity.getRoles().remove(roleEntity);
    }

    @Override
    public void deleteRole(Long id, String roleName) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity roleEntity = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userEntity.getRoles().remove(roleEntity);
    }


    @Transactional
    public void updateState(Long id, UserState state) {

        Optional<UserEntity> user =
                userRepository.findById(id);

        if (user.isPresent()) {

            user.get().setState(state);

        } else {

            throw new AuthenticationException(
                    "User does not exist");

        }
    }
    @Transactional
    public List<User>getAllUsers(){
        log.info("Obteniendo todos los usuarios");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .toList();
    }

    @Override
    // El objetivo de este método es encontrar un usuario en el respositorio usando su correo electrónico que es una llave unica.

    // UserDetails es una interfaz de spring security que sirve para que esta pueda obtener el username, password y authorities. Más adelante servirá para procesar el jwt

    // SimpleGrantedAuthority es una clase de spring security que sirve para representar un rol o un permiso de un usuario.
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        User user = userMapper.toModel(userEntity.get());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }
}