package com.cognixia.jump.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.List;
import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.cognixia.jump.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private UserController controller;
	
	@Test
	void testGetUsers() throws Exception {
		String uri = STARTING_URI +"/user";
		List<User> allUsers = Arrays.asList(new User(), new User());
		
		when( controller.getUsers()).thenReturn(allUsers);
		
		mvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
		
		verify(controller, times(1)).getUsers();
		verifyNoMoreInteractions(controller);
	}
	
	@Test
	void testCreateUser() throws Exception {
		String uri = STARTING_URI +"/user";
		
		User user = new User(); 
		
		mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content( asJsonString(user)))
				.andDo( print() ).andExpect(status().isCreated());
		
		
	}

	public static String asJsonString( final Object obj ) {
		
		try {
			return new ObjectMapper().writeValueAsString(obj);
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
