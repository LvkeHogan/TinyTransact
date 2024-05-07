package com.lukehogan.tinytransact.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lukehogan.tinytransact.jpa.AccountRepository;
import com.lukehogan.tinytransact.jpa.CardRepository;
import com.lukehogan.tinytransact.jpa.TransactionRepository;

@RestController
public class JpaResource {
	
	private AccountRepository accountRepository;
	private CardRepository cardRepository;
	private TransactionRepository transactionRepository;
	
	//Constructor
	@Autowired
	public JpaResource(AccountRepository accountRepository, CardRepository cardRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.cardRepository = cardRepository;
		this.transactionRepository = transactionRepository;
	}
	
	//***API Methods***
	
	//**Accounts**
	// TODO Show All Accounts
	
	
	// TODO Get specific account by number
	
	
	// TODO Create new account
	
	
	// TODO Close existing account
	
	//**Cards**
	// TODO Issue new card
	// Method POST /card
	// Inputs: Account Number, Desired Type (amex, mastercard etc)
	// Outputs: Newly Issued Card Number
	
	
	// TODO Close existing card
	// Method POST /card/issue
	// Inputs: Card number
	// Outputs: None
	
	// TODO Reissue existing card
	// Method POST /card/issue
	// Inputs: Current card number
	// Outputs: Updated card number
	
	
	
	//**Transactions**
	// TODO Account deposit
	// Method PATCH /deposit
	// Inputs: Account Number, Desired Type (amex, mastercard etc)
	// Outputs: new balance
	
	// TODO Account withdraw
	
	
	// TODO Credit card transaction
	
	
	// TODO Credit card refund
	
	
	
	
}
