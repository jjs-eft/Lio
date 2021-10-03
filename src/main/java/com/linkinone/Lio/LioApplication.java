package com.linkinone.Lio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LioApplication.class, args);
	}

}
