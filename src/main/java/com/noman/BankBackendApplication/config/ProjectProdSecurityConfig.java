package com.noman.BankBackendApplication.config;

import com.noman.BankBackendApplication.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.noman.BankBackendApplication.exceptionhandling.CustomeAccessDeniedHandler;
import com.noman.BankBackendApplication.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class ProjectProdSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, RestTemplateBuilder restTemplateBuilder) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsfig = new CorsConfiguration();
                        corsfig.setAllowedOrigins(Collections.singletonList("https://localhost:4200"));
                        corsfig.setAllowedMethods(Collections.singletonList("*"));
                        corsfig.setAllowCredentials(true);
                        corsfig.setAllowedHeaders(Collections.singletonList("*"));
                        corsfig.setMaxAge(3600L);
                        return corsfig;
                    }
                }))
                .requiresChannel((r) -> r.anyRequest().requiresInsecure()) //not HTTPS
                .csrf(csrfConfig -> csrfConfig
                        .ignoringRequestMatchers("/contact","/register")
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(rcc-> rcc.anyRequest().requiresInsecure())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT") //for all those route authentication needed
                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS") //for all those route authentication needed
                        .requestMatchers("/myBalance").hasAuthority("VIEWBALANCE") //for all those route authentication needed
                        .requestMatchers( "/myLoans").hasAuthority("VIEWLOANS") //for all those route authentication needed
                        .requestMatchers( "/user").authenticated() //for all those route authentication needed
                        .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession").permitAll());  //no authentication needed
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomeAccessDeniedHandler()));

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}