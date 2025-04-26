package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class UserController {
    
    @Autowired
    private  UserRepository userRepository;

    
    @PostMapping("/register")
    public User registUser(@RequestBody User user){
       return userRepository.save(user);
    }
}
