package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.jwt.JwtUtil;
import com.uade.tpo.demo.request.LoginRequest;
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

    @GetMapping("/admin/")
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

    @PutMapping("/EditUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userService.getUser(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());

            User savedUser = userService.updateUser(existingUser).getBody();

            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/Register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists.");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists.");
        }

        User createdUser = userService.createUser(user);


        String token = jwtUtil.generateToken(createdUser.getUsername(), createdUser.getRole());


        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        User user = userService.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {

            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());


            return ResponseEntity.ok(new AuthResponse(token));
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);


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
