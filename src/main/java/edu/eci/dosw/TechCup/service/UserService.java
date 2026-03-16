package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    private UserRepository userRepository;
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){}
    public User createUser(User user){
        user.setId(idGenerator.getAndIncrement());
        userRepository.save(user);
        return user;
    }
    public User searchUserByEmail(String mail) {
        Optional<User> u = userRepository.findByEmail(mail);
        return u.orElse(null);
    }

    public Optional<User> searchUserById(Long id) {
        Optional<User> u = userRepository.findById(id);
        return Optional.ofNullable(u.orElse(null));
    }

    public User searchUserByIdentification(String identification) {
        User u = null;
        return u;
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
