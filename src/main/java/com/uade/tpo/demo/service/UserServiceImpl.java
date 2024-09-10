package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Article;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findByUsername(String username) {
        User userFinded = userRepository.findByUsername(username);
        return userFinded;
    }

    @Override
    public User findByEmail(String email) {
        User userFinded = userRepository.findByEmail(email);
        return userFinded;
        }

    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok("article deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("article not found.");
        }
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setRole(user.getRole());

            User updatedUser = userRepository.save(userToUpdate);

            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
