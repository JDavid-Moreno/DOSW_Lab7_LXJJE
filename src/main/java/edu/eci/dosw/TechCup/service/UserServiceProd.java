package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.mapper.UserMapper;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
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

    public UserServiceProd(
            UserRepository userRepository,
            UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    public void updateRole(Long id, Role role) {

        Optional<UserEntity> user =
                userRepository.findById(id);

        if (user.isPresent()) {

            user.get().setRole(role);

        } else {

            throw new AuthenticationException(
                    "User does not exist");

        }
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }
}