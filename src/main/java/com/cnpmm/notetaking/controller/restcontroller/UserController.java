package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<User>> getListUser(){
        return ResponseEntity.ok().body(userService.getListUser());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addNewUser(user));
    }

    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Integer id,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password){
        userService.updateUser(id,email,password);
        return ResponseEntity.ok().build();
    }
}
