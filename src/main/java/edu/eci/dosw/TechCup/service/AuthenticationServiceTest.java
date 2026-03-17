package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class AuthenticationServiceTest implements AuthenticationService {
    @Override
    public User login(String email, String password) {

    }

    @Override
    public User register(User user) {

    }
}
