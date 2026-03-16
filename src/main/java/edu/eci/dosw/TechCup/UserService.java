package edu.eci.dosw.TechCup;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){}
    public User searchUserByEmail(String mail) {
        User u = userRepository.findByEmail(mail);
        return u;
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
