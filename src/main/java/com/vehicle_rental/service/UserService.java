package com.vehicle_rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vehicle_rental.model.User;
import com.vehicle_rental.repository.UserRepository;
@Service
public class UserService {
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    public User registerUser(User user) {
	        if (userRepository.existsByUsername(user.getUsername())) {
	            throw new RuntimeException("Username already exists");
	        }
	        if (userRepository.existsByEmail(user.getEmail())) {
	            throw new RuntimeException("Email already registered");
	        }
	        
	        // Store password as plain text (UNSAFE)
	        user.setPassword(user.getPassword());
	        return userRepository.save(user);
	    }
	    
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }
	}

