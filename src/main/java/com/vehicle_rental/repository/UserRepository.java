package com.vehicle_rental.repository;




	
	import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle_rental.model.User;

import java.util.Optional;

	public interface UserRepository extends JpaRepository<User, Long> {
	    Optional<User> findByUsername(String username);
	    Optional<User> findByEmail(String email);
	    boolean existsByUsername(String username);
	    boolean existsByEmail(String email);
	}

