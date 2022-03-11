package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.CurrentUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api")
@RestController
@Tag(name = "User", description="Controller to manage users")
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	
	@Autowired
	PasswordEncoder encoder;
	
	@Operation(summary = "admin only endpoint for retrieving users")
	@GetMapping("/user")
	public List<User> getUsers(){
		return repo.findAll();
	}
	
	@Operation(summary = "unused")
	@GetMapping("/CurrentUser")
	public ResponseEntity<?> getCurrentUser(){
		return ResponseEntity.status(200).body(CurrentUserService.getUser()==null);
	}
	
	@Operation(summary = "Open endpoint for creating users")
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		user.setId(null);
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		user.setEnabled(true);
		
		System.out.println(user);
		
		User newUser = repo.save(user);
		
		return ResponseEntity.status(201).body("User "+newUser.getUsername()+" created");
	}
}
