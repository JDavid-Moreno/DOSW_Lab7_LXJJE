package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.exception.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){}
    @Transactional
    public User searchUserByEmail(String mail) {
        Optional<User> u = userRepository.findByEmail(mail);
        return u.orElse(null);
    }
    @Transactional
    public Optional<User> searchUserById(Long id) {
        Optional<User> u = userRepository.findById(id);
        return Optional.ofNullable(u.orElse(null));
    }
    @Transactional
    public User searchUserByIdentification(String identification) {
        User u = null;
        return u;
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

    public Team searchUserTeam(Long id){
        Team e = null;
        return e;
    }
    public Team searchUserTeam(String mail){
        Team e = null;
        return e;
    }


}
