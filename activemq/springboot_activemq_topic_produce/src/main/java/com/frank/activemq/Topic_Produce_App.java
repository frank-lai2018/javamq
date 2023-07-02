package com.frank.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Topic_Produce_App {

	public static void main(String[] args) {
		SpringApplication.run(Topic_Produce_App.class, args);
	}

}
