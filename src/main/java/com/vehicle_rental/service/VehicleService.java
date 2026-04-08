package com.vehicle_rental.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.vehicle_rental.model.Vehicle;
import com.vehicle_rental.repository.VehicleRepository;

import java.util.List;

	@Service
	public class VehicleService {
	    @Autowired
	    private VehicleRepository vehicleRepository;
	    
	    public List<Vehicle> getAllVehicles() {
	        return vehicleRepository.findAll();
	    }
	    
	    public List<Vehicle> getAvailableVehicles() {
	        return vehicleRepository.findByAvailable(true);
	    }
	    
	    public List<Vehicle> getVehiclesByType(Vehicle.VehicleType type) {
	        return vehicleRepository.findByType(type);
	    }
	    
	    public Vehicle getVehicleById(Long id) {
	        return vehicleRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
	    }
	    
	    public Vehicle saveVehicle(Vehicle vehicle) {
	        return vehicleRepository.save(vehicle);
	    }
	    
	    public void deleteVehicle(Long id) {
	        vehicleRepository.deleteById(id);
	    }
	}

