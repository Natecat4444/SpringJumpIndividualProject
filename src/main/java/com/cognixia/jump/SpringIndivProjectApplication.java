package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Monitor retail api", version = "0.9", description="API for the cognixia jump program for managing a online monitor store"))
public class SpringIndivProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIndivProjectApplication.class, args);
	}

}
