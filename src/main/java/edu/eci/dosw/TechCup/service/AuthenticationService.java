package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;
    public AuthenticationService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    };
    public String login(String mail, String password){
        password = encoder.encode(password);
        return "123";
    };
}
