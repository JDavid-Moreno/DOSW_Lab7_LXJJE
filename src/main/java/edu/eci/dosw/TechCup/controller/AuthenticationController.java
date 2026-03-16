package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(){
        this.authenticationService = new AuthenticationService(new BCryptPasswordEncoder(12));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user){
        authenticationService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody User user){
        authenticationService.login(user);
        return ResponseEntity.ok().build();
    }
}
