package com.cognixia.jump.service;

import com.cognixia.jump.model.User;

public class CurrentUserService {
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		CurrentUserService.user = user;
		System.out.println("User set");
	}
	
	

}
