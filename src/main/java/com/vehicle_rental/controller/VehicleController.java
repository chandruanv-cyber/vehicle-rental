package com.vehicle_rental.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

import com.vehicle_rental.model.Vehicle;
import com.vehicle_rental.service.VehicleService;

	@Controller
	@RequestMapping("/vehicles")
	public class VehicleController {
	    @Autowired
	    private VehicleService vehicleService;
	    
	    @GetMapping
	    public String listVehicles(Model model) {
	        model.addAttribute("vehicles", vehicleService.getAllVehicles());
	        return "vehicles/list";
	    }
	    
	    @GetMapping("/add")
	    public String showAddForm(Model model) {
	        model.addAttribute("vehicle", new Vehicle());
	        return "vehicles/add";
	    }
	    
	    @PostMapping("/add")
	    public String addVehicle(@ModelAttribute Vehicle vehicle) {
	        vehicleService.saveVehicle(vehicle);
	        return "redirect:/vehicles";
	    }
	    
	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        model.addAttribute("vehicle", vehicleService.getVehicleById(id));
	        return "vehicles/edit";
	    }
	    
	    @PostMapping("/edit/{id}")
	    public String updateVehicle(@PathVariable Long id, @ModelAttribute Vehicle vehicle) {
	        vehicle.setId(id);
	        vehicleService.saveVehicle(vehicle);
	        return "redirect:/vehicles";
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String deleteVehicle(@PathVariable Long id) {
	        vehicleService.deleteVehicle(id);
	        return "redirect:/vehicles";
	    }
	}

