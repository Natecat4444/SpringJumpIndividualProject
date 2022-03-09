package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.service.CurrentUserService;
import com.cognixia.jump.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api")
@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Operation(summary = "Creates a new jet token given a correct username and password")
	@PostMapping("/login")
	public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch(BadCredentialsException e) {
			throw new Exception("Invalid login");
		}
		
		final UserDetails userD = userDetailsService.loadUserByUsername(request.getUsername());
		
		final String jwt = jwtUtil.generateTokens(userD);
				
		return ResponseEntity.status(201).body(new AuthenticationResponse(jwt));
	}
	
	@Operation(summary = "don't use")
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		CurrentUserService.setUser(null);
		return ResponseEntity.status(201).body("Current user destroyed");
	}
}
