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
	// Method GET /accounts
	// Inputs: None
	// Outputs: All accounts in JSON
	
	
	// TODO Get specific account by number
	// Method POST /accounts
	// Inputs: Account Number (request body)
	// Outputs: Account Details
	
	
	// TODO Create new account
	// Method POST /accounts/create
	// Inputs: name, address, phone number, initial deposit, etc.
	// Outputs: Newly Issued Account Number
	
	
	// TODO Close existing account
	// Method POST /accounts/close
	// Inputs: account number
	// Outputs: returned funds
	
	
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
	// Method PATCH /accounts/deposit
	// Inputs: Account Number, deposit amount (has to be 2 decimal places)
	// Outputs: new balance
	
	
	// TODO Account withdraw
	// Method PATCH /accounts/withdraw
	// Inputs: Account Number, withdraw amount (has to be 2 decimal places)
	// Outputs: new balance
	
	
	// TODO Credit card charge
	// Method PATCH /card/charge
	// Inputs: Credit card number, charge amount (has to be 2 decimal places)
	// Outputs: charge amount or insufficient funds
	
	
	// TODO Credit card refund
	// Method PATCH /card/refund
	// Inputs: Credit card number, refund amount (has to be 2 decimal places)
	// Outputs: charge amount or insufficient funds
	
	
	
	
}
