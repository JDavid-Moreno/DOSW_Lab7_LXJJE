package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return userService.searchUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){

    }
}
