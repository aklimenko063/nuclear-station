package com.javaacademy.nuclearstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Runner {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
		NuclearStation beanNuclearStation = context.getBean(NuclearStation.class);
		beanNuclearStation.start(3);
	}
}
