package com.lukehogan.tinytransact.jpa;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Transaction;

//Spring Data JPA implementation
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	//Find transactions by account id
	Optional<List<Transaction>> findByAccountId(int accountId);
	
	//Find transactions by card id
	Optional<List<Transaction>> findByCardId(Long cardId);
	
	//Find transactions by datetime range
	Optional<List<Transaction>> findByTimeBetween(OffsetDateTime beginTime, OffsetDateTime endTime);
	
	//Find Account transactions by datetime range
	Optional<List<Transaction>> findByAccountAndTimeBetween(int accountNum, OffsetDateTime beginTime, OffsetDateTime endTime);
	
	//Find Card transactions by datetime range
	Optional<List<Transaction>> findByCardAndTimeBetween(Long cardNum, OffsetDateTime beginTime, OffsetDateTime endTime);
	
}

//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html