package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import java.util.List;

import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> searchUserByEmail(String email);
    Optional<User> searchUserById(Long id);
    Optional<User> searchUserByIdentification(String identification);
    void updateRole(Long id, Role role);
    void updateState(Long id, UserState state);
}
