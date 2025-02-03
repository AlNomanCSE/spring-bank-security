package com.noman.security.controller;

import com.noman.security.model.Customer;
import com.noman.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            String encode = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(encode);
            Customer saved = customerRepository.save(customer);

            if(saved != null){
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An Exception Occurred"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
