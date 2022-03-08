package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository repo;
	
	private String Username;

	// method will be called by spring security whena request comes in credentials(username + password from the request will be loaded in
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		this.Username = username;
		Optional<User> userFound = repo.findByUsername(username);
		
		if(userFound.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		CurrentUserService.setUser(userFound.get());
		return new MyUserDetails(userFound.get());
	}


	public String getUsername() {
		return Username;
	}
	
	

}
