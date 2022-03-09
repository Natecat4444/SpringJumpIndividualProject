package com.cognixia.jump.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MonitorController.class)
public class MonitorControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private MonitorController controller;
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Test
	void testCreateMonitor() {
		
	}
	
	@Test
	void testGetMonitors() {
		
	}
	
	@Test
	void testGetMonitor() {
		
	}
	
	@Test
	void testGetMonitorByBrand() {
		
	}
	
	@Test
	void testGetMonitorBySize() {
		
	}
	
	@Test
	void testGetMonitorBySizeGreaterThan() {
		
	}
	
	@Test
	void testGetMonitorBySizeAndBrand() {
		
	}
	
	@Test
	void testGetMonitorBySizeAndBrandGreaterThan() {
		
	}
}
