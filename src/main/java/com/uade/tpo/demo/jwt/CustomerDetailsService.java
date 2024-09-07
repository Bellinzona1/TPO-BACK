package com.uade.tpo.demo.jwt;


import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    private User userDetail;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername {} ", username);
        userDetail = userService.findByUsername(username);

        if (!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(userDetail.getUsername(),userDetail.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }

    public User getUserDetail() {
        return userDetail;
    }
}
