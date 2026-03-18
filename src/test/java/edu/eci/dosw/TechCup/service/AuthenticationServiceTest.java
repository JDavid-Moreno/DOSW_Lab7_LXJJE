package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Profile("test")
public class AuthenticationServiceTest implements AuthenticationService {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User login(String email, String password) {
        User user = users.get(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null; 
    }

    @Override
    public User register(User user) {
        users.put(user.getEmail(), user);
        return user;
    }
}