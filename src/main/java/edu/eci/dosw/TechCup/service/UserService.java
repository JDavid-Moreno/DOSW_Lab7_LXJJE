package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.exception.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> searchUserByEmail(String mail) {
        return userRepository.findByEmail(mail);
    }
    @Transactional
    public Optional<User> searchUserById(Long id) {
            return userRepository.findById(id);
    }
    @Transactional
    public Optional<User> searchUserByIdentification(String identification) {
        return userRepository.findByIdentification(identification);
    }
    @Transactional
    public void updateRole(Long id, Role role) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setRole(role);
        }
        else {
            throw new AuthenticationException("User does not exist");
        }
    }
    @Transactional
    public void updateState(Long id, UserState state) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setState(state);
        }
        else {
            throw new AuthenticationException("User does not exist");
        }
    }
}
