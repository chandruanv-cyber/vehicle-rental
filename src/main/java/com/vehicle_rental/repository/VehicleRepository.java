package com.vehicle_rental.repository;


	

	
	import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle_rental.model.Vehicle;

import java.util.List;

	public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	    List<Vehicle> findByType(Vehicle.VehicleType type);
	    List<Vehicle> findByAvailable(boolean available);
	    List<Vehicle> findByMakeContainingIgnoreCase(String make);
	    List<Vehicle> findByModelContainingIgnoreCase(String model);
	}

