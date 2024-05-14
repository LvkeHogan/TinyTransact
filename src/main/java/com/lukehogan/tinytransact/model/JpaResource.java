package com.lukehogan.tinytransact.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lukehogan.tinytransact.exception.BadRequestException;
import com.lukehogan.tinytransact.exception.NotFoundException;
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
	
	//Show All Accounts
	// Method GET /accounts
	// Inputs: None
	// Outputs: All accounts in JSON
	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	
	// TODO Get specific account by number
	// Method POST /accounts
	// Inputs: Account Number (request body)
	// Outputs: Account Details
	@PostMapping("/accounts")
	public Account getAccountByNum(@RequestBody AccountRequest request) {
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
		
		// TODO this is a dummy for now. Need to handle exception if no account is returned.
		return foundAccount.get();
		
		
	}
	
	
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
	
	
	// TODO get all transactions
	////Method GET /transactions
	
	//Get transactions by date range
	//Method POST /transactions/date
	//Inputs: Start date, End date
	//Outputs: List of transactions within the range, for all cards and accounts
	@PostMapping("/transactions/daterange")
	public List<Transaction> getTransactionByDateRange(@RequestBody TransactionLookupRequest request){
		OffsetDateTime beginTime = request.getBeginTimestamp();
		OffsetDateTime endTime = request.getEndTimestamp();
		
		//Check Valid Inputs
		if(beginTime == null || endTime == null) {
			throw new BadRequestException("Both timestamps must be provided; refer to documentation.");
		}
		
		
		if(beginTime.isAfter(endTime)) {
			throw new BadRequestException("Begin timestamp must be before End timestamp.");
		}
		
		//if start and end date are the same, set the end date to the end of the day. We want values exclusive of the next day.
		if(beginTime.equals(endTime)) {
			endTime = endTime.plusDays(1L);
		}
		
		if(request.getAccountNum() != null || request.getCardNum() != null) {
			throw new BadRequestException("This endpoint does not handle card or account inputs; refer to documentation.");
		}
		
		
		//Query for the matching transactions
		Optional<List<Transaction>> results = transactionRepository.findByTimeBetween(beginTime,endTime);
		
		if(results.isEmpty()) {
			throw new NotFoundException(beginTime.toString() + ", " + endTime.toString());
		}
		else {
			return results.get();
		}

	}
	
	
	// TODO get transactions by card and date range. Handle empty dates as wanting all.
	//Method POST /transactions/card
	//Inputs: Card Number
	//Outputs: List of transactions corresponding to the card.
	@PostMapping("/transactions/card")
	public List<Transaction> getTransactionsByCard(@RequestBody TransactionLookupRequest request){
		Long cardNum = request.getCardNum();
		OffsetDateTime beginTime = request.getBeginTimestamp();
		OffsetDateTime endTime = request.getEndTimestamp();
		Optional<List<Transaction>> results;
		
		
		//Check Inputs
		if(cardNum == null) {
			throw new BadRequestException("Card number must be provided.");
		}
		if(beginTime == null && endTime != null || beginTime != null && endTime == null) {
			throw new BadRequestException("Both timestamps must be provided.");
		}
		if(beginTime.isAfter(endTime)) {
			throw new BadRequestException("Begin timestamp must be before End timestamp.");
		}
		if(beginTime.equals(endTime)) {
			endTime = endTime.plusDays(1L);
		}
		
		//Query for the data.
		
		if(beginTime == null && endTime == null) {
			results = transactionRepository.findByCardId(cardNum);
		}
		else {
			results = transactionRepository.findByCardAndTimeBetween(cardNum, beginTime, endTime);
		}
		
		if(results.isEmpty()) {
			throw new NotFoundException(cardNum.toString());
		}
		
		return results.get();
		
		
		
		
		
		
	}
	
	// TODO get transactions by account. Handle empty dates as wanting all.
	//Method POST /transactions/account
	//Inputs: Card Number, Start date, End date
	//Outputs: List of transactions within the range, for all cards and accounts
	
}
