package com.example.project1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project3Application {

	private static final Logger log = LoggerFactory.getLogger(Project3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(SweaterWarehouse warehouse) {
		return (args) -> {

			log.info("-------------------------------");
			log.info("Log says something :)");
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			log.info("-------------------------------");

		};
	}


}
