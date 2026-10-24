package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import java.util.List;

import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> searchUserByEmail(String email);
    Optional<User> searchUserById(Long id);
    Optional<User> searchUserByIdentification(String identification);
    void updateState(Long id, UserState state);
    User addRole(Long id, Long roleId);
    User addRole(Long id, String roleName);
    void deleteRole(Long id, Long roleId);
    void deleteRole(Long id, String roleName);
}
