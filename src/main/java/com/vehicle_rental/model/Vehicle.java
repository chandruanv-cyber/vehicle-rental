package com.vehicle_rental.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "vehicle_type", nullable = false)
	    private VehicleType type;
	    
	    @Column(nullable = false)
	    private String make;
	    
	    @Column(nullable = false)
	    private String model;
	    
	    @Column(nullable = false)
	    private Integer year;
	    
	    private String color;
	    
	    @Column(name = "registration_number", nullable = false, unique = true)
	    private String registrationNumber;
	    
	    @Column(name = "daily_rate", nullable = false)
	    private BigDecimal dailyRate;
	    
	    @Column(nullable = false)
	    private Boolean available = true;
	    
	    @Column(name = "image_path")
	    private String imagePath;
	    
	    
	    public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public VehicleType getType() {
			return type;
		}


		public void setType(VehicleType type) {
			this.type = type;
		}


		public String getMake() {
			return make;
		}


		public void setMake(String make) {
			this.make = make;
		}


		public String getModel() {
			return model;
		}


		public void setModel(String model) {
			this.model = model;
		}


		public Integer getYear() {
			return year;
		}


		public void setYear(Integer year) {
			this.year = year;
		}


		public String getColor() {
			return color;
		}


		public void setColor(String color) {
			this.color = color;
		}


		public String getRegistrationNumber() {
			return registrationNumber;
		}


		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}


		public BigDecimal getDailyRate() {
			return dailyRate;
		}


		public void setDailyRate(BigDecimal dailyRate) {
			this.dailyRate = dailyRate;
		}


		public Boolean isAvailable() {
			return available;
		}


		public void setAvailable(Boolean available) {
			this.available = available;
		}


		public String getImagePath() {
			return imagePath;
		}


		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}


		public enum VehicleType {
	        CAR, BIKE
	    }
	}

