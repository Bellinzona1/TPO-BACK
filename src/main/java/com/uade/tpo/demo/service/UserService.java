package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService {

    public ArrayList<User> getUsers();

    public Optional<User> getUser(Long id);

    public User createUser(User user);

    public User findByUsername(String username);

    public ResponseEntity<User> updateUser(User user);


}
