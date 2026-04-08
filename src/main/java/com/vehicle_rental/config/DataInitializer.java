package com.vehicle_rental.config;


	import org.springframework.boot.CommandLineRunner;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import com.vehicle_rental.model.User;
import com.vehicle_rental.repository.UserRepository;

	@Configuration
	public class DataInitializer {
		@Bean
		CommandLineRunner initDatabase(UserRepository userRepository) {
		    return args -> {
		        if (userRepository.count() == 0) {
		            User admin = new User();
		            admin.setUsername("admin");
		            admin.setPassword("admin123"); // Plain text
		            admin.setEmail("admin@test.com");
		            admin.setFullName("Admin User");
		            admin.setRole(User.Role.ADMIN);
		            userRepository.save(admin);
		            
		            User customer = new User();
		            customer.setUsername("customer");
		            customer.setPassword("customer123"); // Plain text
		            customer.setEmail("customer@test.com");
		            customer.setFullName("John Customer");
		            customer.setRole(User.Role.CUSTOMER);
		            userRepository.save(customer);
		        }
		    };
		}
	    }
	

