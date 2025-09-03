package com.askeruzmani.asker_uzmani_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AskerUzmaniBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AskerUzmaniBackendApplication.class, args);
	}

}
