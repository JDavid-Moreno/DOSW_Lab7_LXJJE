package edu.eci.dosw.TechCup.service;
import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.exception.AuthenticationException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Profile("prod")
public class UserServiceProd implements UserService {

    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserServiceProd.class);

    public UserServiceProd(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> searchUserByEmail(String mail) {
        log.info("Buscando usuario por email: {}", mail);

        Optional<UserEntity> user = userRepository.findByEmail(mail);

        if (user.isPresent()) {
            log.info("Usuario encontrado con email: {}", mail);
        } else {
            log.warn("Usuario no encontrado con email: {}", mail);
        }

        return user;
    }

    @Transactional
    public Optional<User> searchUserById(Long id) {
        log.info("Buscando usuario por id: {}", id);

        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            log.info("Usuario encontrado con id: {}", id);
        } else {
            log.warn("Usuario no encontrado con id: {}", id);
        }

        return user;
    }

    @Transactional
    public Optional<User> searchUserByIdentification(String identification) {
        log.info("Buscando usuario por identificación: {}", identification);

        Optional<UserEntity> user = userRepository.findByIdentification(identification);

        if (user.isPresent()) {
            log.info("Usuario encontrado con identificación: {}", identification);
        } else {
            log.warn("Usuario no encontrado con identificación: {}", identification);
        }

        return user;
    }

    @Transactional
    public void updateRole(Long id, Role role) {
        log.info("Actualizando rol del usuario con id: {} a {}", id, role);

        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setRole(role);
            log.info("Rol actualizado correctamente para el usuario con id: {}", id);
        }
        else {
            log.warn("No se pudo actualizar rol: usuario no existe con id {}", id);
            throw new AuthenticationException("User does not exist");
        }
    }

    @Transactional
    public void updateState(Long id, UserState state) {
        log.info("Actualizando estado del usuario con id: {} a {}", id, state);

        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setState(state);
            log.info("Estado actualizado correctamente para el usuario con id: {}", id);
        }
        else {
            log.warn("No se pudo actualizar estado: usuario no existe con id {}", id);
            throw new AuthenticationException("User does not exist");
        }
    }
}
