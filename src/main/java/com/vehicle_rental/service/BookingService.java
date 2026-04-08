package com.vehicle_rental.service;


	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle_rental.model.Booking;
import com.vehicle_rental.model.User;
import com.vehicle_rental.model.Vehicle;
import com.vehicle_rental.repository.BookingRepository;

import java.math.BigDecimal;
	import java.time.LocalDate;
	import java.time.temporal.ChronoUnit;
	import java.util.List;

	@Service
	public class BookingService {
	    @Autowired
	    private BookingRepository bookingRepository;
	    
	    @Autowired
	    private VehicleService vehicleService;
	    
	    public Booking createBooking(User user, Long vehicleId, LocalDate startDate, LocalDate endDate) {
	        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
	        
	        if (!vehicle.isAvailable()) {
	            throw new RuntimeException("Vehicle is not available for booking");
	        }
	        
	        long days = ChronoUnit.DAYS.between(startDate, endDate);
	        BigDecimal totalCost = vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));
	        
	        Booking booking = new Booking();
	        booking.setUser(user);
	        booking.setVehicle(vehicle);
	        booking.setStartDate(startDate);
	        booking.setEndDate(endDate);
	        booking.setTotalCost(totalCost);
	        booking.setStatus(Booking.BookingStatus.PENDING);
	        
	        return bookingRepository.save(booking);
	    }
	    
	    public List<Booking> getUserBookings(User user) {
	        return bookingRepository.findByUser(user);
	    }
	    
	    public Booking cancelBooking(Long bookingId) {
	        Booking booking = bookingRepository.findById(bookingId)
	                .orElseThrow(() -> new RuntimeException("Booking not found"));
	        
	        booking.setStatus(Booking.BookingStatus.CANCELLED);
	        return bookingRepository.save(booking);
	    }
	    
	    public List<Booking> getAllBookings() {
	        return bookingRepository.findAll();
	    }
	    
	    public Booking updateBookingStatus(Long bookingId, Booking.BookingStatus status) {
	        Booking booking = bookingRepository.findById(bookingId)
	                .orElseThrow(() -> new RuntimeException("Booking not found"));
	        
	        booking.setStatus(status);
	        return bookingRepository.save(booking);
	    }
	}

