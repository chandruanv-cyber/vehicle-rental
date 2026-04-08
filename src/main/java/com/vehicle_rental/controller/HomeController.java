package com.vehicle_rental.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;

import com.vehicle_rental.model.Vehicle;
import com.vehicle_rental.service.VehicleService;

import java.util.List;

	@Controller
	public class HomeController {
	    @Autowired
	    private VehicleService vehicleService;
	    
	    @GetMapping("/")
	    public String home(Model model) {
	        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
	        model.addAttribute("vehicles", availableVehicles);
	        return "index";
	    }
	}

