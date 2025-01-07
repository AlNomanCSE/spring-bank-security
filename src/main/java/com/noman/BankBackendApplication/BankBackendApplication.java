package com.noman.BankBackendApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.noman.BankBackendApplication.config","com.noman.BankBackendApplication.controller"})
public class BankBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankBackendApplication.class, args);
	}
}


