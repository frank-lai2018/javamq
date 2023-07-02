package com.frank.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Queue_Produce_App {

	public static void main(String[] args) {
		SpringApplication.run(Queue_Produce_App.class, args);
	}

}
