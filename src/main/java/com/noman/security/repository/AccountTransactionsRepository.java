package com.noman.security.repository;

import com.noman.security.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);
}
