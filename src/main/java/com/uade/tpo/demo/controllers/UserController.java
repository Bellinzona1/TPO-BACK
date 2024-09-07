package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.jwt.JwtUtil;
import com.uade.tpo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    JwtUtil jwtUtil ;

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

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/CreateUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Crear el usuario en la base de datos
        User createdUser = userService.createUser(user);

        // Generar el token JWT
        String token = jwtUtil.generateToken(createdUser.getUsername(), createdUser.getRole());

        // Retornar el token en la respuesta
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Clase para encapsular la respuesta con el token
    public class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }



}
