package com.cognixia.jump.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Monitor;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User_Order;
import com.cognixia.jump.repository.User_OrderRepository;

public class OrderControllerTest {
private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private OrderController controller;
	
	@MockBean
	User_OrderRepository repo;
	
	@Test
	void testGetOrders() {
		String uri = STARTING_URI + "/orders";
		
		List<User_Order> orders = Arrays.asList(new User_Order(null, null, new User(), new Monitor(null, "Acer", 10.5, 0, 10.5 , ""), null) );
		
		when (repo.getOrderByUser() ).thenReturn(orders);
	}
	
	@Test
	void testCreateOrder() {
		//TODO
	}
	
	@Test
	void testCreateOrder2() {
		//TODO
	}
	
	@Test
	void testUpdateStatus() {
		//TODO
	}
}
