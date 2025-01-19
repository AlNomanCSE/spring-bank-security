package com.noman.BankBackendApplication.config;

import com.noman.BankBackendApplication.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.noman.BankBackendApplication.exceptionhandling.CustomeAccessDeniedHandler;
import com.noman.BankBackendApplication.filter.CsrfCookieFilter;
import com.noman.BankBackendApplication.filter.JWTTokenGeneratorFilter;
import com.noman.BankBackendApplication.filter.JWTTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsfig = new CorsConfiguration();
                        corsfig.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsfig.setAllowedMethods(Collections.singletonList("*"));
                        corsfig.setAllowCredentials(true);
                        corsfig.setAllowedHeaders(Collections.singletonList("*"));
                        corsfig.setExposedHeaders(Arrays.asList("Authorization"));
                        corsfig.setMaxAge(3600L);
                        return corsfig;
                    }
                }))
                .requiresChannel((r) -> r.anyRequest().requiresInsecure()) //not HTTPS
                .csrf(csrfConfig -> csrfConfig
                        .ignoringRequestMatchers("/contact", "/register","/apiLogin")
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT") //for all those route authentication needed
//                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS") //for all those route authentication needed
//                        .requestMatchers("/myBalance").hasAuthority("VIEWBALANCE") //for all those route authentication needed
//                        .requestMatchers( "/myLoans").hasAuthority("VIEWLOANS") //for all those route authentication needed
                        .requestMatchers("/myAccount").hasAnyRole("USER", "ADMIN") //for all those route authentication needed
                        .requestMatchers("/myCards").hasAnyRole("USER", "ADMIN") //for all those route authentication needed
                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN") //for all those route authentication needed
                        .requestMatchers("/myLoans").authenticated() //for all those route authentication needed
                        .requestMatchers("/user").authenticated() //for all those route authentication needed
                        .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession","/apiLogin").permitAll());  //no authentication needed
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomeAccessDeniedHandler()));
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        BankUsernamePwdAuthenticationProvider authenticationProvider = new BankUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}