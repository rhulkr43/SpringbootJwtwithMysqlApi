package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UserRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRestApiApplication.class, args);
	}

}
