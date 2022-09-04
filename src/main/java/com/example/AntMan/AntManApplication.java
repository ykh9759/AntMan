package com.example.AntMan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AntManApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntManApplication.class, args);
	}

}
