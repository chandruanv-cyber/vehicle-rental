package com.vehicle_rental.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle_rental.model.Booking;
import com.vehicle_rental.model.User;

import java.util.List;

	public interface BookingRepository extends JpaRepository<Booking, Long> {
	    List<Booking> findByUser(User user);
	    List<Booking> findByVehicleId(Long vehicleId);
	    List<Booking> findByStatus(Booking.BookingStatus status);
	}

