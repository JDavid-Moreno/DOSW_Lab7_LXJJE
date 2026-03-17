package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("test")
public class UserServiceTest implements UserService {
    @Override
    public Optional<User> searchUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> searchUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> searchUserByIdentification(String identification) {
        return Optional.empty();
    }

    @Override
    public void updateRole(Long id, Role role) {

    }

    @Override
    public void updateState(Long id, UserState state) {

    }
}
