package com.lukehogan.tinytransact.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Transaction;

//Spring Data JPA implementation
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	//Find transactions by account id
	Optional<List<Transaction>> findByAccountId(int accountId);
	
	//Find transactions by card id
	Optional<List<Transaction>> findByCardId(int cardId);
	
	
	// TODO find transactions by day
	
	
	// TODO find transactions by datetime range
	
	
}
