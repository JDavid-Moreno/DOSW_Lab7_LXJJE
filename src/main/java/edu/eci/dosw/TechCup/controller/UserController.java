package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.State;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(){
        this.userService = new UserService();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        //si se encuentra: 200 ok, si no: 404 not found
        Optional<User> user = userService.searchUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}/role")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestBody Role role){
        userService.updateRole(id, role);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("{id}/state")
    public ResponseEntity<User> updateState(@PathVariable Long id, @RequestBody UserState state){
        Optional<User> user = userService.searchUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
