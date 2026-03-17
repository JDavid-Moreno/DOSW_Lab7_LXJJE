package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class AuthenticationServiceTest implements AuthenticationService {
    @Override
    public void login(User user) {

    }

    @Override
    public void register(User user) {

    }
}
