package com.noman.BankBackendApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication

//-------if package is different-------//
@EnableJpaRepositories("com.noman.BankBackendApplication.repository")
@EntityScan("com.noman.BankBackendApplication.model")
@EnableMethodSecurity(jsr250Enabled = true,securedEnabled = true)
public class BankBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankBackendApplication.class, args);
	}
}


