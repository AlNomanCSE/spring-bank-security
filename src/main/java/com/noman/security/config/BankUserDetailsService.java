package com.noman.security.config;

import com.noman.security.model.Customer;
import com.noman.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {
    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found :" + email));
        return new User(customer.getEmail(),customer.getPwd(), List.of(new SimpleGrantedAuthority(customer.getRole())));
    }
}
