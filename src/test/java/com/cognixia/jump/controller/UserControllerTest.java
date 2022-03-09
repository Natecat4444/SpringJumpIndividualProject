package com.cognixia.jump.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private UserController controller;
	
	@Test
	void testGetUsers() {
		//TODO
	}
	
	@Test
	void testCreateUser() {
		//TODO
	}
}
