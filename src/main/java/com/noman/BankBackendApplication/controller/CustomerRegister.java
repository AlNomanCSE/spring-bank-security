package com.noman.BankBackendApplication.controller;

import com.noman.BankBackendApplication.model.Customer;
import com.noman.BankBackendApplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRegister {

    private  final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        try{
          String encodedPassword = passwordEncoder.encode(customer.getPwd());
          customer.setPwd(encodedPassword);
          Customer savedCustomer = customerRepository.save(customer);
          if(savedCustomer.getId()>0){
              return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
          }else {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register customer");
          }
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" An Error occurred :"+ e.getMessage());
        }
    }
}
