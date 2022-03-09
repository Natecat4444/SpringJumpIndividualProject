package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JwtRequestFilter;
import com.cognixia.jump.service.MyUserDetails;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/user").permitAll()
			.antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/login").permitAll()
			.antMatchers(HttpMethod.GET, "/api/CurrentUser").authenticated()
			.antMatchers(HttpMethod.POST, "/api/monitor").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/monitors").authenticated()
			.antMatchers(HttpMethod.GET, "/api/monitor/{id}").authenticated()
			.antMatchers(HttpMethod.GET, "/api/monitor/brand/{brand}").authenticated()
			.antMatchers(HttpMethod.GET, "/monitor/size/{size}").authenticated()
			.antMatchers(HttpMethod.GET, "/monitor/size/gt/{size}").authenticated()
			.antMatchers(HttpMethod.GET, "/monitor/brand/gt/{brand}/{size}").authenticated()
			.antMatchers(HttpMethod.GET, "/monitor/brand/{brand}/{size}").authenticated()
			.antMatchers(HttpMethod.GET, "/api/order").authenticated()
			.antMatchers(HttpMethod.POST, "/api/order/create/{mon_id}/{quantity}").authenticated()
			.antMatchers(HttpMethod.POST, "/api/order/add/{mon_id}/{quantity}/{ordernum}").authenticated()
			.antMatchers(HttpMethod.PATCH, "/orders/{status}").authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
//	@Bean
//	public MyUserDetails userDetails() {
//		return userDetailsService.loadUserByUsername((userDetailsService).getUsername());
//	}
}
