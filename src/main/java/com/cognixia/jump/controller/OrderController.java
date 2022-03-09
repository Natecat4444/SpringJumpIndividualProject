package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.InvalidUserException;
import com.cognixia.jump.model.Monitor;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User_Order;
import com.cognixia.jump.repository.MonitorRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.repository.User_OrderRepository;
import com.cognixia.jump.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;

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
	MonitorRepository mrepo;
	
	@Autowired
	JwtUtil jwt;
	
	@Operation(summary = "Get the user's order history", description = "Gets the user from the token and runs a query to find that user's orders")
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(HttpServletRequest req){
		String token = req.getHeader("Authorization").split(" ")[1];
		Optional<User> user = urepo.findByUsername(jwt.extractUsername(token));
		if(!user.isEmpty()) {
			System.out.println(user.get().getId().getClass());
			List<User_Order> orders = repo.getOrdersByUser(user.get());
			return ResponseEntity.status(200).body(orders);
		}
		return ResponseEntity.status(403).body("No valid user");
	}
	
	@Operation(summary = "Creates a brand new order")
	@PostMapping("/orders/create/{mon_id}/{quantity}")
	public ResponseEntity<?> createOrder(@PathVariable Integer mon_id, @PathVariable int quantity, HttpServletRequest req) throws Exception{
		String token = req.getHeader("Authorization").split(" ")[1];
		Optional<User> user = urepo.findByUsername(jwt.extractUsername(token));
		if(user.isEmpty()) {
			throw new InvalidUserException("No valid user in token");
		}
		Optional<Monitor> monitor = mrepo.findById(mon_id);
		if(monitor.isEmpty()) {
			return ResponseEntity.status(404).body("Invalid Monitor Id");
		}
		User_Order order = new User_Order(null, quantity, user.get(), monitor.get(), null);
		User_Order saved = repo.save(order);
		return ResponseEntity.status(201).body(saved);
	}
	
	@Operation(summary = "If an order contains more than one type of monitor, all additional types will be added here")
	@PostMapping("/orders/add/{mon_id}/{quantity}/{ordernum}")
	public ResponseEntity<?> createOrder(@PathVariable Integer mon_id, @PathVariable int quantity, @PathVariable int ordernum,HttpServletRequest req)throws Exception{
		String token = req.getHeader("Authorization").split(" ")[1];
		Optional<User> user = urepo.findByUsername(jwt.extractUsername(token));
		if(user.isEmpty()) {
			throw new InvalidUserException("No valid user in token");
		}
		Optional<Monitor> monitor = mrepo.findById(mon_id);
		if(monitor.isEmpty()) {
			return ResponseEntity.status(404).body("Invalid Monitor Id");
		}
		User_Order order = new User_Order(null, quantity, user.get(), monitor.get(), ordernum);
		User_Order saved = repo.save(order);
		return ResponseEntity.status(201).body(saved);
	}
	
	@Operation(summary = "An admin only endpoint for updating where the order is in status, by default all orders are recieved")
	@PatchMapping("/orders/{status}")
	public ResponseEntity<?> updateStatus(HttpServletRequest req, @PathVariable Integer ordernum, @PathVariable User_Order.Status status)throws Exception{
		String token = req.getHeader("Authorization").split(" ")[1];
		Optional<User> user = urepo.findByUsername(jwt.extractUsername(token));
		if(user.isEmpty()) {
			throw new InvalidUserException("No valid user in token");
		}
		List<User_Order> orders = repo.getOrdersByOrdernum(ordernum);
		if(orders.size() == 0) {
			return ResponseEntity.status(404).body("Ordernum is invalid");
		}
		
		for(User_Order o : orders) {
			o.setStatus(status);
			repo.save(o);
		}
		orders = repo.getOrdersByOrdernum(ordernum);
		return ResponseEntity.status(200).body(orders);
	}
}
