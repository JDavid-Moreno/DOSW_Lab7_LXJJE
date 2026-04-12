package edu.eci.dosw.TechCup.model;

public class AuthRequest {
    private String username;
    private String password;

    public String getEmail() {
        return username;
    }
    public void setEmail(String email) {
        this.username = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
