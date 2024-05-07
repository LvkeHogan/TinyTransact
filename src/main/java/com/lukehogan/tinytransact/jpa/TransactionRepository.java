package com.lukehogan.tinytransact.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Transaction;

//Spring Data JPA implementation
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
}
