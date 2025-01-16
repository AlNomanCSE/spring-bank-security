package com.noman.BankBackendApplication.repository;

import com.noman.BankBackendApplication.model.Contacts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contacts,String> {

}
