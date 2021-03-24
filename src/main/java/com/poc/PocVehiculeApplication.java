package com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
public class PocVehiculeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocVehiculeApplication.class, args);
	}

}
