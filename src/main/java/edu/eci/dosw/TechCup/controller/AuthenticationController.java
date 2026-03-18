package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User created = authenticationService.register(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User logged = authenticationService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(logged);
    }
}
