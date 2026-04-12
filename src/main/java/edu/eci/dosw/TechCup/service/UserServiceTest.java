package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Profile("test")
public class UserServiceTest implements UserService {

    private Map<Long, User> usersById = new HashMap<>();
    private Map<String, User> usersByEmail = new HashMap<>();
    private Map<String, User> usersByIdentification = new HashMap<>();
    private Map<Long, Role> rolesById = new HashMap<>();
    private Map<String, Role> rolesByName = new HashMap<>();


    private Long idCounter = 1L;

    public User save(User user) {
        user.setId(idCounter++);
        usersById.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
        usersByIdentification.put(user.getIdentification(), user);
        return user;
    }

    @Override
    public Optional<User> searchUserByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    @Override
    public Optional<User> searchUserById(Long id) {
        return Optional.ofNullable(usersById.get(id));
    }

    @Override
    public Optional<User> searchUserByIdentification(String identification) {
        return Optional.ofNullable(usersByIdentification.get(identification));
    }

    @Override
    public User addRole(Long id, Long roleId) {
        rolesById.put(0L,new Role(0L,"ROL_JUGADOR"));
        User user = usersById.get(id);
        if (user != null) {
            user.getRoles().add(rolesById.get(roleId));
        }
        return user;
    }
    @Override
    public User addRole(Long id, String roleName) {
        rolesByName.put("ROL_JUGADOR",new Role(0L,"ROL_JUGADOR"));
        User user = usersById.get(id);
        if (user != null) {
            user.getRoles().add(rolesByName.get(roleName));
        }
        return user;
    }

    @Override
    public void deleteRole(Long id, Long roleId) {
        User user = usersById.get(id);
        if (user != null) {
            user.getRoles().remove(rolesById.get(roleId));
        }
    }

    @Override
    public void deleteRole(Long id, String roleName) {
        User user = usersById.get(id);
        if (user != null) {
            user.getRoles().remove(rolesByName.get(roleName));
        }
    }

    @Override
    public void updateState(Long id, UserState state) {
        User user = usersById.get(id);
        if (user != null) {
            user.setState(state);
        }
    }
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

}