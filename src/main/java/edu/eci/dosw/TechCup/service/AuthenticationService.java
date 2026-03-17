package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;

public interface AuthenticationService {
    void login(User user);
    void register(User user);
}
