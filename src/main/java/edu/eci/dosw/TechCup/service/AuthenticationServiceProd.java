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
import java.util.concurrent.atomic.AtomicLong;
@Service
@Profile("prod")
public class AuthenticationServiceProd implements AuthenticationService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public AuthenticationServiceProd(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    };
    @Transactional
    public void login(User user){
        String mail = user.getEmail();
        String password = user.getPassword();
        User storedUser = userRepository.findByEmail(mail).orElseThrow(() -> new AuthenticationException("Email does not exist"));
        if (!encoder.matches(password, storedUser.getPassword())) {
            throw new AuthenticationException("Wrong password");
        }

    };
    @Transactional
    public void register(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (userRepository.existsByEmail(email)){
            throw new AuthenticationException("Email already in use.");
        }
        user.setId(idGenerator.getAndIncrement());
        user.setPassword(encoder.encode(password));
        user.setRole(Role.JUGADOR);
        user.setState(UserState.ACTIVE);
        userRepository.save(user);
    }


}
