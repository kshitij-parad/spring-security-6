package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.entity.User;

public interface UserRepository extends   JpaRepository<User, Integer>{
    
}
