package com.noman.BankBackendApplication.repository;

import com.noman.BankBackendApplication.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts,Long> {
    Accounts findByCustomerId(long customerId);
}
