package com.noman.BankBackendApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myCards", "/myBalance","/myLoans").authenticated() //for all those route authentication needed
                .requestMatchers("/notices", "/contact","/error").permitAll());  //no authentication needed
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}