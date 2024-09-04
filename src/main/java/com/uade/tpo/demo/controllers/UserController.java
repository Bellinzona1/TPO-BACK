package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional getUserById (@PathVariable Long id) {
        return userService.getUser(id);

    }

    @PostMapping("/CreateUser")
    public ResponseEntity<User> createUser (@RequestBody User user) {
        return userService.createUser(user);


    }
}
