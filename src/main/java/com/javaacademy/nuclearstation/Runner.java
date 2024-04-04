package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.economicdepartment.EconomicDepartment;
import com.javaacademy.nuclearstation.economicdepartment.FranceEconomicDepartment;
import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Runner {
	public static int DAYS_OF_YEAR = 365;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);

		NuclearStation beanNuclearStation = context.getBean(NuclearStation.class);

		beanNuclearStation.start(3);
		EconomicDepartment economicDepartment = beanNuclearStation.getEconomicDepartment();

	}

}
