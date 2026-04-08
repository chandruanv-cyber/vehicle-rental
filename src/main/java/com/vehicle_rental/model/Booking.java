package com.vehicle_rental.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "bookings")
public class Booking {
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;
	    
	    @ManyToOne
	    @JoinColumn(name = "vehicle_id", nullable = false)
	    private Vehicle vehicle;
	    
	    @Column(name = "booking_date")
	    private LocalDateTime bookingDate;
	    
	    @Column(name = "start_date", nullable = false)
	    private LocalDate startDate;
	    
	    @Column(name = "end_date", nullable = false)
	    private LocalDate endDate;
	    
	    @Column(name = "total_cost", nullable = false)
	    private BigDecimal totalCost;
	    
	    @Enumerated(EnumType.STRING)
	    private BookingStatus status;
	    
	    
	    public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public Vehicle getVehicle() {
			return vehicle;
		}


		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}


		public LocalDateTime getBookingDate() {
			return bookingDate;
		}


		public void setBookingDate(LocalDateTime bookingDate) {
			this.bookingDate = bookingDate;
		}


		public LocalDate getStartDate() {
			return startDate;
		}


		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}


		public LocalDate getEndDate() {
			return endDate;
		}


		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}


		public BigDecimal getTotalCost() {
			return totalCost;
		}


		public void setTotalCost(BigDecimal totalCost) {
			this.totalCost = totalCost;
		}


		public BookingStatus getStatus() {
			return status;
		}


		public void setStatus(BookingStatus status) {
			this.status = status;
		}


		public enum BookingStatus {
	        PENDING, CONFIRMED, CANCELLED, COMPLETED
	    }
	}

