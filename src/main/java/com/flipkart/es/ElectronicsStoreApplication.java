package com.flipkart.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ElectronicsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicsStoreApplication.class, args);
	}

}
