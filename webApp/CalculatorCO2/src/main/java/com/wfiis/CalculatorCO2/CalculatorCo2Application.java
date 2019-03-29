package com.wfiis.CalculatorCO2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CalculatorCo2Application {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorCo2Application.class, args);
	}

}
