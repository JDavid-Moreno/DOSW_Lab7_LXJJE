package edu.eci.dosw.TechCup.service;
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
public class AuthenticationServiceProd implements AuthenticationService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceProd.class);

    public AuthenticationServiceProd(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    };

    @Transactional
    public User login(String email, String password){
        log.info("Intentando autenticación para el email: {}", email);

        User storedUser = userRepository.findByEmail(email).orElseThrow(() -> {
            log.warn("Intento de login con email no registrado: {}", email);
            return new AuthenticationException("Email does not exist");
        });

        if (!encoder.matches(password, storedUser.getPassword())) {
            log.warn("Contraseña incorrecta para el email: {}", email);
            throw new AuthenticationException("Wrong password");
        }

        log.info("Autenticación exitosa para el usuario: {}", email);
        return storedUser;
    }

    @Transactional
    public User register(User user) {
        String email = user.getEmail();
        log.info("Intentando registrar usuario con email: {}", email);

        if (userRepository.existsByEmail(email)){
            log.warn("Registro fallido: email ya en uso {}", email);
            throw new AuthenticationException("Email already in use.");
        }

        String password = user.getPassword();
        String hashedPassword = encoder.encode(password);
        Role role = Role.JUGADOR;
        UserState state = UserState.ACTIVE;

        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setState(state);

        User savedUser = userRepository.save(user);

        log.info("Usuario registrado exitosamente con email: {}", email);
        return savedUser;
    }
}
