package com.example.accessingdata;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class AccessingDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataApplication.class, args);
	}

}
