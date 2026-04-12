package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.mapper.UserMapper;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.exception.AuthenticationException;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Profile("prod")
public class AuthenticationServiceProd
        implements AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationServiceProd.class);

    public AuthenticationServiceProd(
            PasswordEncoder encoder,
            UserRepository userRepository,
            UserMapper userMapper) {

        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public User login(String email, String password){

        UserEntity storedUser =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new AuthenticationException(
                                        "Email does not exist"));

        if (!encoder.matches(
                password,
                storedUser.getPassword())) {

            throw new AuthenticationException(
                    "Wrong password");
        }

        return userMapper.toModel(storedUser);
    }

    @Transactional
    public User register(User user) {

        String email = user.getEmail();

        if (userRepository.existsByEmail(email)) {

            throw new AuthenticationException(
                    "Email already in use.");
        }

        user.setPassword(
                encoder.encode(user.getPassword()));

        user.setRoles(Role.JUGADOR);
        user.setState(UserState.ACTIVE);

        UserEntity savedUser =
                userRepository.save(
                        userMapper.toEntity(user));

        return userMapper.toModel(savedUser);
    }
}