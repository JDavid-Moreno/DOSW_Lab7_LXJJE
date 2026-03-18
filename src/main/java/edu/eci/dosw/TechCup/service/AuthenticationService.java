package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;

public interface AuthenticationService {
    User login(String email, String password);
    User register(User user);
}
