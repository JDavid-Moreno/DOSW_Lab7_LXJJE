package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones de registro e inicio de sesión")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario con rol jugador por defecto")
    public ResponseEntity<User> register(@RequestBody User user){
        User created = authenticationService.register(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario comparando correo y contraseña")
    public ResponseEntity<User> login(@RequestBody User user){
        User logged = authenticationService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(logged);
    }
}
