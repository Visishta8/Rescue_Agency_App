package com.sih.rescueApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RescueAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RescueAppApplication.class, args);
	}

}
