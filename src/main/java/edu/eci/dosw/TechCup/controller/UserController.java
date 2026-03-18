package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario dado su ID, 404 si no existe")
    public ResponseEntity<User> get(@PathVariable Long id){
        //si se encuentra: 200 ok, si no: 404 not found
        Optional<User> user = userService.searchUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/role")
    @Operation(summary = "Actualizar rol", description = "Solo el administrador puede cambiar el rol de un usuario")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestBody Role role){
        userService.updateRole(id, role);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}/state")
    @Operation(summary = "Inactivar usuario", description = "Actualiza el estado del usuario")
    public ResponseEntity<User> updateState(@PathVariable Long id, @RequestBody UserState state){
        userService.updateState(id,state);
        return ResponseEntity.noContent().build();
    }

}
