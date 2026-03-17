package edu.eci.dosw.TechCup;

public interface UserRepository {
    User findByEmail(String email);
}
