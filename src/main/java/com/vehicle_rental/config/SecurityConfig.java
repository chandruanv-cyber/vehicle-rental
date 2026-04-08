package com.vehicle_rental.config;


	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.crypto.password.NoOpPasswordEncoder;
	import com.vehicle_rental.model.User;
import com.vehicle_rental.repository.UserRepository;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
	                .requestMatchers("/vehicles/**", "/bookings/**").authenticated()
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	            )
	            .formLogin(form -> form
	                .loginPage("/login")
	                .defaultSuccessUrl("/")
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutSuccessUrl("/")
	                .permitAll()
	            );
	        
	        return http.build();
	    }
	    
	    @Bean
	    public UserDetailsService userDetailsService(UserRepository userRepository) {
	        return username -> {
	            User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	            
	            return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRole().name())
	                .build();
	        };
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance(); // UNSAFE - for testing only
	    }
	}

