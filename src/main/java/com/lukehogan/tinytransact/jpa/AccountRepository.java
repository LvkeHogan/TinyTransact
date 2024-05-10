package com.lukehogan.tinytransact.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukehogan.tinytransact.model.Account;

//Spring Data JPA implementation
public interface AccountRepository extends JpaRepository<Account, Integer> {

	//Custom method to get account by number.
	Optional<Account> findByAccountNumber(int accountNumber);

}
