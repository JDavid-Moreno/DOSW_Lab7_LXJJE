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

@Service
@Profile("prod")
public class AuthenticationServiceProd implements AuthenticationService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;

    public AuthenticationServiceProd(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    };
    @Transactional
    public User login(String email, String password){
        User storedUser = userRepository.findByEmail(email).orElseThrow(() -> new AuthenticationException("Email does not exist"));
        if (!encoder.matches(password, storedUser.getPassword())) {
            throw new AuthenticationException("Wrong password");
        }
        return storedUser;
    }
    @Transactional
    public User register(User user) {
        String email = user.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new AuthenticationException("Email already in use.");
        }
        String password = user.getPassword();
        String hashedPassword = encoder.encode(password);
        Role role = Role.JUGADOR;
        UserState state = UserState.ACTIVE;
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setState(state);
        return userRepository.save(user);
    }
}
