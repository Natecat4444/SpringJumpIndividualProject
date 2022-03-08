package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User_Order;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.repository.User_OrderRepository;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class OrderController {
	@Autowired
	User_OrderRepository repo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	UserDetailsService userDetailsService; 
	
	@Autowired
	JwtUtil jwt;
	
	@GetMapping("/orders")
	public List<User_Order> getAllOrders(HttpServletRequest req){
		String token = req.getHeader("Authorization").split(" ")[1];
		Optional<User> user = urepo.findByUsername(jwt.extractUsername(token));
		if(!user.isEmpty()) {
			System.out.println(user.get().getId());
		}
		return null;
	}
}
