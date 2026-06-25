package com.example.dijkstra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.dijkstra.controller.MainController;


@SpringBootApplication
public class DijkstraApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DijkstraApplication.class, args);
		MainController con = context.getBean(MainController.class);
		con.run();
	}
}
