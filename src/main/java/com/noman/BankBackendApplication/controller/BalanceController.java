package com.noman.BankBackendApplication.controller;

import com.noman.BankBackendApplication.model.AccountTransactions;
import com.noman.BankBackendApplication.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> balanceDetails = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if (balanceDetails != null) {
            return balanceDetails;
        } else {
            return null;
        }
    }

}
