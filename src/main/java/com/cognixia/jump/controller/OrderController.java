package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User_Order;
import com.cognixia.jump.repository.User_OrderRepository;

@RequestMapping("/api")
@RestController
public class OrderController {
	@Autowired
	User_OrderRepository repo;
	
	@Autowired
	UserDetailsService userDetailsService; 
	
	@GetMapping("/orders")
	public List<User_Order> getAllOrders(){
		return null;
	}
}
