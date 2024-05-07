package com.lukehogan.tinytransact.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Card;

//Spring Data JPA implementation
public interface CardRepository extends JpaRepository<Card, Integer> {

}
