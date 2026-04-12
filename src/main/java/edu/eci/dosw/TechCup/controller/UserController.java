package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

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
    @PostMapping("/{id}/rol")
    @Operation(summary = "Añadir rol por id", description = "Solo el administrador puede cambiar el rol de un usuario")
    public ResponseEntity<User> addRole(@PathVariable Long id, @RequestBody Long roleId){
        User user = userService.addRole(id, roleId);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/{id}/rol")
    @Operation(summary = "Añadir rol nombre", description = "Solo el administrador puede cambiar el rol de un usuario")
    public ResponseEntity<User> addRole(@PathVariable Long id, @RequestBody String roleName){
        User user = userService.addRole(id, roleName);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}/rol/{roleId}")
    @Operation(summary = "Eliminar rol por id", description = "Solo el administrador puede eliminar el rol de un usuario")
    public ResponseEntity<Void> removeRole(@PathVariable Long id, @PathVariable Long roleId){
        userService.deleteRole(id, roleId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/rol/{roleName}")
    @Operation(summary = "Eliminar rol por nombre", description = "Solo el administrador puede eliminar el rol de un usuario")
    public ResponseEntity<Void> removeRole(@PathVariable Long id, @PathVariable String roleName){
        userService.deleteRole(id, roleName);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}/state")
    @Operation(summary = "Inactivar usuario", description = "Actualiza el estado del usuario")
    public ResponseEntity<User> updateState(@PathVariable Long id, @RequestBody UserState state){
        userService.updateState(id,state);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
