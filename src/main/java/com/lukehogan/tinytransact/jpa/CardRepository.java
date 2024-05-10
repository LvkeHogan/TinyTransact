package com.lukehogan.tinytransact.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Card;

//Spring Data JPA implementation
public interface CardRepository extends JpaRepository<Card, Integer> {
	
	//Find cards by account ID
	Optional<List<Card>> findByAccountId(int accountId);
	
	//Find card by card number
	Optional<Card> findByCardNum(long cardNum);
	
	
	
	
}
