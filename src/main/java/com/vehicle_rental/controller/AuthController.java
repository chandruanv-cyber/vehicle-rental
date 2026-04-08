package com.vehicle_rental.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;

import com.vehicle_rental.model.User;
import com.vehicle_rental.service.UserService;

	@Controller
	public class AuthController {
	    @Autowired
	    private UserService userService;
	    
	    @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	    
	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }
	    
	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute("user") User user, Model model) {
	        try {
	            userService.registerUser(user);
	            return "redirect:/login?registered";
	        } catch (RuntimeException e) {
	            model.addAttribute("error", e.getMessage());
	            return "register";
	        }
	    }
	}

