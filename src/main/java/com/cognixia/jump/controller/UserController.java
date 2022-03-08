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

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/user")
	public List<User> getUsers(){
		return repo.findAll();
	}
	
	@GetMapping("/CurrentUser")
	public ResponseEntity<?> getCurrentUser(){
		return ResponseEntity.status(200).body(CurrentUserService.getUser()==null);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		user.setId(null);
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		user.setEnabled(true);
		
		User newUser = repo.save(user);
		
		return ResponseEntity.status(201).body("User "+newUser.getUsername()+" created");
	}
}
