package com.noman.security.repository;

import com.noman.security.model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByCustomerId(long customerId);

}