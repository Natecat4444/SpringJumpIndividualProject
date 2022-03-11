package com.cognixia.jump.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Monitor;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.model.User_Order;
import com.cognixia.jump.repository.MonitorRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.repository.User_OrderRepository;
import com.cognixia.jump.util.JwtUtil;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public class OrderControllerTest {
private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private OrderController controller;
	
	@MockBean
	User_OrderRepository repo;
	
	@MockBean
	MonitorRepository mrepo;
	
	@MockBean
	UserDetailsService userDetailService;
	
	
	@MockBean
	JwtUtil jwtUtil;
	
	@MockBean
	UserRepository userRepository;
	
	@Test
	@WithMockUser(username="test",roles= {"USER"})
	void testGetOrders() throws Exception {
		String uri = STARTING_URI + "/orders";
		
		List<User_Order> orders = Arrays.asList(new User_Order(null, null, new User(), new Monitor(null, "Acer", 10.5, 0, 10.5 , ""), null) );
		User user = new User();
		user.setRole(Role.ROLE_USER);
		user.setUsername("test");
		
		when (repo.getOrdersByUser(any(User.class))).thenReturn(orders);
		
		mvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="test",roles= {"USER"})
	void testCreateOrder() {
		String uri = STARTING_URI + "/orders/create/{mon_id}/{quantity}";
		
		when (repo.save(any(User_Order.class))).thenReturn(new User_Order());
		//TODO
	}
	
	@Test
	@WithMockUser(username="test",roles= {"USER"})
	void testCreateOrder2() {
		String uri = STARTING_URI + "/orders/add/{mon_id}/{quantity}/{ordernum}";
		//TODO
	}
	
	@Test
	@WithMockUser(username="test",roles= {"USER"})
	void testUpdateStatus() {
		String uri = STARTING_URI + "/orders/{status}";
		//TODO
	}
}
