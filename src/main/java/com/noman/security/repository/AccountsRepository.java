package com.noman.security.repository;

import com.noman.security.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts,Long> {
    Accounts findByCustomerId(long customerId);
}
