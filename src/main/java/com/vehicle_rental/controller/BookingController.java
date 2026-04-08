package com.vehicle_rental.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

import com.vehicle_rental.model.Booking;
import com.vehicle_rental.model.User;
import com.vehicle_rental.service.BookingService;
import com.vehicle_rental.service.UserService;
import com.vehicle_rental.service.VehicleService;

import java.time.LocalDate;

	@Controller
	@RequestMapping("/bookings")
	public class BookingController {
	    @Autowired
	    private BookingService bookingService;
	    
	    @Autowired
	    private VehicleService vehicleService;
	    
	    @Autowired
	    private UserService userService;
	    
	    @GetMapping
	    public String listBookings(Model model) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByUsername(auth.getName());
	        
	        if (user.getRole() == User.Role.ADMIN) {
	            model.addAttribute("bookings", bookingService.getAllBookings());
	        } else {
	            model.addAttribute("bookings", bookingService.getUserBookings(user));
	        }
	        
	        return "bookings/list";
	    }
	    
	    @GetMapping("/new/{vehicleId}")
	    public String showBookingForm(@PathVariable Long vehicleId, Model model) {
	        model.addAttribute("vehicle", vehicleService.getVehicleById(vehicleId));
	        model.addAttribute("booking", new Booking());
	        return "bookings/new";
	    }
	    
	    @PostMapping("/new/{vehicleId}")
	    public String createBooking(@PathVariable Long vehicleId, 
	                               @RequestParam LocalDate startDate,
	                               @RequestParam LocalDate endDate,
	                               Model model) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByUsername(auth.getName());
	        
	        try {
	            bookingService.createBooking(user, vehicleId, startDate, endDate);
	            return "redirect:/bookings";
	        } catch (RuntimeException e) {
	            model.addAttribute("error", e.getMessage());
	            model.addAttribute("vehicle", vehicleService.getVehicleById(vehicleId));
	            return "bookings/new";
	        }
	    }
	    
	    @GetMapping("/cancel/{id}")
	    public String cancelBooking(@PathVariable Long id) {
	        bookingService.cancelBooking(id);
	        return "redirect:/bookings";
	    }
	    
	    @GetMapping("/status/{id}/{status}")
	    public String updateBookingStatus(@PathVariable Long id, 
	                                     @PathVariable Booking.BookingStatus status) {
	        bookingService.updateBookingStatus(id, status);
	        return "redirect:/bookings";
	    }
	}

