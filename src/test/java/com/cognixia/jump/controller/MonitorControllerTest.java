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
		//TODO
	}
	
	@Test
	void testGetMonitors() {
		//TODO
	}
	
	@Test
	void testGetMonitor() {
		//TODO
	}
	
	@Test
	void testGetMonitorByBrand() {
		//TODO
	}
	
	@Test
	void testGetMonitorBySize() {
		//TODO
	}
	
	@Test
	void testGetMonitorBySizeGreaterThan() {
		//TODO
	}
	
	@Test
	void testGetMonitorBySizeAndBrand() {
		//TODO
	}
	
	@Test
	void testGetMonitorBySizeAndBrandGreaterThan() {
		//TODO
	}
}
