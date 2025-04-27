package com.spring.security.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;


@RestController
public class UserController {
    
    @Autowired
    private  UserRepository userRepository;

    
    @PostMapping("/register")
    public User registUser(@RequestBody User user){

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
       return userRepository.save(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user){

        User user1 = userRepository.findByUsername(user.getUsername());
        if(!Objects.isNull(user1)){
            return "success!!";
        }
        return "failed to login!!";
    }
}
