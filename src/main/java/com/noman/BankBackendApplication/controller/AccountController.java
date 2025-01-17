package com.noman.BankBackendApplication.controller;

import com.noman.BankBackendApplication.model.Accounts;
import com.noman.BankBackendApplication.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam long id) {
        Accounts account = accountsRepository.findByCustomerId(id);
        if (account != null) {
            return account;
        } else {
            return null;
        }

    }

}
