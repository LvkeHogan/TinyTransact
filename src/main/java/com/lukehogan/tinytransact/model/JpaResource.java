package com.lukehogan.tinytransact.model;

import com.lukehogan.tinytransact.exception.BadRequestException;
import com.lukehogan.tinytransact.exception.NotFoundException;
import com.lukehogan.tinytransact.jpa.AccountRepository;
import com.lukehogan.tinytransact.jpa.CardRepository;
import com.lukehogan.tinytransact.jpa.TransactionRepository;
import com.lukehogan.tinytransact.transact.transact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	
	
	//Get specific account by number
	// Method POST /accounts
	// Inputs: Account Number (request body)
	// Outputs: Account Details
	@PostMapping("/accounts")
	public Account getAccountByNum(@RequestBody AccountRequest request) {
		Integer acctNum = request.getAccountNumber();
		if(acctNum == null) {
			throw new BadRequestException("Account number must be provided");
		}
		
		Optional<Account> result = accountRepository.findByAccountNumber(acctNum);
		
		if(result.isEmpty()) {
			throw new NotFoundException(acctNum.toString());
		}
		return result.get();
		
		
	}
	
	
	// Create new account
	// Method POST /accounts/create
	// Inputs: name, address, phone number, initial deposit, etc. in a class designed to take the input
	// Outputs: Newly Issued Account Number
	@PostMapping("/accounts/create")
	public int createAccount(@RequestBody Account newAccount) {
		//ensure required inputs are provided
		if(newAccount.getFirstName() == null || newAccount.getLastName() == null || newAccount.getAddress() == null || newAccount.getCity() == null || newAccount.getState() == null || newAccount.getZipCode() == null || newAccount.getPhoneNum() == null) {
			throw new BadRequestException("First name, Last Name, Address components and Phone Number must be provided.");
		}
		int newAcctNum = 0;
		
		//Generate an account number
		boolean unique = false;
		while(unique == false) {
			newAcctNum = transact.generateAcctNum();
			Optional<Account> test = accountRepository.findByAccountNumber(newAcctNum);
			if(test.isEmpty()) {
				unique = true;
			}
		}
				
		//Update new account with its account number
		newAccount.setAccountNumber(newAcctNum);
		
		//Persist it to the database
		accountRepository.save(newAccount);
		
		//return the account number
		return newAcctNum;
		
	}
	
	// Delete an account
	// Method DELETE /accounts/
	// Inputs: first name, last name, account number
	// Outputs: deleted account balance (cashout)
	@DeleteMapping("/accounts")
	public double deleteAccount(@RequestBody AccountRequest request) {
		//Check inputs not null
		if(request.getFirstName() == null || request.getLastName() == null || request.getAccountNumber() == null) {
			throw new BadRequestException("Account number, First name and Last name must be provided.");
		}
		//see if account exists. if it doesn't, throw an exception.
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
		if(foundAccount.isEmpty()) {
			throw new NotFoundException(request.getAccountNumber().toString() + " Does Not Exist.");
		}
		//Save the account balance to return
		BigDecimal balance = foundAccount.get().getBalance();

		// TODO delete all cards linked to the account. Just use the method we'll make for deleting cards.
		
		//Delete the account
		accountRepository.delete(foundAccount.get());
		
		return balance.setScale(2, RoundingMode.CEILING).floatValue();
		
	}
	
	
	// Modify an existing account
	// Method PATCH /accounts/update
	// Inputs: (Mandatory)account number (Optional) first name, last name, address, city, state, phone number
	// Outputs: none
	@PatchMapping("/accounts/update")
	public void modifyAccount(@RequestBody AccountRequest request) {
		//Validate required input
		if(request.getAccountNumber() == null) {
			throw new BadRequestException("Account number must be provided");
		}
		
		//See if account exists. If it doesn't, throw an exception
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
		if(foundAccount.isEmpty()) {
			throw new NotFoundException(request.getAccountNumber().toString() + " Does Not Exist.");
		}
		Account account = foundAccount.get();
		
		//For all the not null provided fields in request, update the corresponding field in the target account.
		if(request.getAddress() != null) {
			account.setAddress(request.getAddress());
		}
		if(request.getCity() != null) {
			account.setCity(request.getCity());
		}
		if(request.getState() != null) {
			account.setState(request.getState());
		}
		if(request.getZipCode() != null) {
			account.setZipCode(request.getZipCode());
		}
		if(request.getPhoneNum() != null) {
			account.setPhoneNum(request.getPhoneNum());
		}
		
	}
	
	
	
	//**Transaction Execution Methods**
	
	// TODO Credit Card Charge
	// Method POST /card/charge
	// Inputs: Card number, charge amount
	// Outputs: Card Type, charge amount
	
	
	
	// TODO Credit Card Refund
	// Method POST /card/refund
	// Inputs: Card number, refund amount
	// Outputs: Card Type, refund amount
	
	
	
	// TODO Account Deposit
	// Method POST /accounts/deposit
	// Inputs: Account number, deposit amount
	// Outputs: Account holder name, deposit amount, balance after deposit
	
	
	
	// TODO Account Withdraw
	// Method POST /accounts/withdraw
	// Inputs: Account number, withdraw amount
	// Outputs: Account holder name, withdraw amount, balance after withdraw
	
	
	
	// TODO Account Transfer
	// Method POST /accounts/transfer
	// Inputs: Account from, Account to, Transfer amount
	// Outputs: from account holder name, to account holder name, transfer amount
	
	
	
	// TODO Account Balance Check
	// Method POST /accounts/balance
	// Inputs: Account number
	// Outputs: Account holder name, current balance
	
	
	
	

	
	
	//**Cards**
	// Issue new card
	// Method POST /card
	// Inputs: Account Number, Desired Type (Fusion, Zap, Posh or Fun). Create unique class to handle.
	// Outputs: Newly Issued Card Number
	@PostMapping("/card")
	public long issueCard(@RequestBody CardRequest request){
		ArrayList<String> cardTypes = new ArrayList<String>(Arrays.asList("FusionCard","ZapCard","PoshCard","FunCard"));

		String type = request.getType();
		Integer accountNumber = request.getAccountNumber();

		//Validate inputs
		if(type == null || accountNumber == null){
			throw new BadRequestException("Account Number and Card Type must be supplied.");
		}
		if(!cardTypes.contains(type)){
			throw new BadRequestException("Available card types: FusionCard, ZapCard, PoshCard, FunCard");
		}
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(accountNumber);
		if(foundAccount.isEmpty()){
			throw new NotFoundException("Account does not exist");
		}

		//Create the new unique card number
		long newCardNumber = 0L;
		do{
			newCardNumber = transact.generateCardNum(type);
		}
		while(cardRepository.findByCardNum(newCardNumber).isPresent());

		//Generate the new card object
		Card newCard = new Card(foundAccount.get(),type,newCardNumber);

		//Save to Database
		cardRepository.save(newCard);
		return newCardNumber;
	}
	
	
	// TODO Close existing card
	// Method POST /card/issue
	// Inputs: Card number
	// Outputs: None
	@DeleteMapping("/card")
	public void closeCard(@RequestBody CardRequest request){
		//Validate Inputs
		Long cardNumber = request.getCardNumber();
		if(cardNumber == null){
			throw new BadRequestException("Card number must be provided");
		}
		if(cardRepository.findByCardNum(cardNumber).isEmpty()){
			throw new NotFoundException("Card does not exist");
		}
		//Perform the close operation
		cardRepository.deleteByCardNum(cardNumber);
	}
	
	
	// Reissue existing card
	// Method POST /card/issue
	// Inputs: Current card number
	// Outputs: Updated card number
	@PatchMapping("/card/reissue")
	public long reissueCard(@RequestBody CardRequest request){
		Long cardNumber = request.getCardNumber();
		//validate inputs
		if(cardNumber.describeConstable().isEmpty()){
			throw new BadRequestException("Card number must be provided.");
		}

		Optional<Card> foundCard = cardRepository.findByCardNum(cardNumber);
		if(foundCard.isEmpty()){
			throw new NotFoundException("Card not found");
		}

		Card currentCard = foundCard.get();
		long newCardNum;
		do {
			newCardNum = transact.generateCardNum(currentCard.getType());
		}
		//Look into exists spring data jpa method. Might clean up lots of our code.
		while(cardRepository.findByCardNum(newCardNum).isPresent());

		currentCard.setCardNum(newCardNum);
		return newCardNum;

	}
	
	
	
	
	//**Transactions**
	
	
	// Account deposit
	// Method PATCH /accounts/deposit
	// Inputs: Account Number, deposit amount (has to be 2 decimal places)
	// Outputs: new balance
	@PatchMapping("/accounts/deposit")
	public double accountDeposit(@RequestBody AccountTransactionRequest request){
		//Validate input
		if(request.getAccountNum() == null || request.getAmount() == null){
			throw new BadRequestException("All fields must be populated");
		}
		Integer accountNum = request.getAccountNum();
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(accountNum);
		if(foundAccount.isEmpty()){
			throw new NotFoundException("Account does not exist");
		}
		//Check that decimal is to 2 decimal places. If not, round. Input should always be 2 decimal places.
		BigDecimal depositAmount = new BigDecimal(request.getAmount()).setScale(2,RoundingMode.FLOOR);

		//Perform the deposit operation to the account.
		Account account = foundAccount.get();
		BigDecimal newBalance = account.getBalance().add(depositAmount);
		account.setBalance(newBalance);

		// TODO Create a transaction record for the deposit
		Transaction record = new Transaction(account,depositAmount,OffsetDateTime.now());

		// TODO send the updated account and transaction to the database
		accountRepository.save(account);
		transactionRepository.save(record);

		return newBalance.doubleValue();
	}
	
	
	// Account withdraw
	// Method PATCH /accounts/withdraw
	// Inputs: Account Number, withdraw amount (has to be 2 decimal places)
	// Outputs: withdrawn amount
	@PatchMapping("/accounts/withdraw")
	public double accountWithdraw(@RequestBody AccountTransactionRequest request){
		//Validate input
		if(request.getAccountNum() == null || request.getAmount() == null){
			throw new BadRequestException("All fields must be populated");
		}
		Integer accountNum = request.getAccountNum();
		Optional<Account> foundAccount = accountRepository.findByAccountNumber(accountNum);
		BigDecimal withdrawAmount = new BigDecimal(request.getAmount()).setScale(2,RoundingMode.FLOOR);
		if(foundAccount.isEmpty()){
			throw new NotFoundException("Account does not exist");
		}
		if(foundAccount.get().getBalance().compareTo(withdrawAmount) <0){
			throw new BadRequestException("Insufficient Funds");
		}


		//Perform the withdraw operation to the account.
		Account account = foundAccount.get();
		BigDecimal newBalance = account.getBalance().subtract(withdrawAmount);
		account.setBalance(newBalance);

		//create a transaction record for the withdraw.
		Transaction record = new Transaction(account,withdrawAmount.multiply(BigDecimal.valueOf(-1)),OffsetDateTime.now());

		//save the updated account and transaction to the database
		accountRepository.save(account);
		transactionRepository.save(record);

		return newBalance.doubleValue();
	}
	
	
	// Credit card charge
	// Method PATCH /card/charge
	// Inputs: Credit card number, charge amount (has to be 2 decimal places)
	// Outputs: charge amount or insufficient funds
	@PatchMapping("/card/charge")
	public CardResult cardCharge(@RequestBody CardTransactionRequest request){
		//Validate input
		if(request.getCardNum() == null || request.getAmount() == null){
			throw new BadRequestException("All fields must be populated");
		}
		Long cardNum = request.getCardNum();
		Optional<Card> foundCard = cardRepository.findByCardNum(cardNum);
		BigDecimal chargeAmount = new BigDecimal(request.getAmount()).setScale(2,RoundingMode.FLOOR);
		if(foundCard.isEmpty()){
			throw new NotFoundException("Card does not exist");
		}
		Card card = foundCard.get();
		//Retrieve the account linked to the card.
		Account linkedAccount = card.getAccount();
		if(linkedAccount.getBalance().compareTo(chargeAmount) <0){
			throw new BadRequestException("Insufficient Funds");
		}


		//Perform the charge operation to the account.
		BigDecimal newBalance = linkedAccount.getBalance().subtract(chargeAmount);
		linkedAccount.setBalance(newBalance);

		//create a transaction record for the withdraw.
		Transaction record = new Transaction(card,linkedAccount,chargeAmount.multiply(BigDecimal.valueOf(-1)),OffsetDateTime.now());

		//save the updated account and transaction to the database
		accountRepository.save(linkedAccount);
		transactionRepository.save(record);

		//return charge amount and card type
		return new CardResult(card.getType(),chargeAmount.doubleValue());
	}

	
	
	// Credit card refund
	// Method PATCH /card/refund
	// Inputs: Credit card number, refund amount (has to be 2 decimal places)
	// Outputs: charge amount or insufficient funds
	@PatchMapping("/card/charge")
	public CardResult cardRefund(@RequestBody CardTransactionRequest request){
		//Validate input
		if(request.getCardNum() == null || request.getAmount() == null){
			throw new BadRequestException("All fields must be populated");
		}
		Long cardNum = request.getCardNum();
		Optional<Card> foundCard = cardRepository.findByCardNum(cardNum);
		BigDecimal refundAmount = new BigDecimal(request.getAmount()).setScale(2,RoundingMode.FLOOR);
		if(foundCard.isEmpty()){
			throw new NotFoundException("Card does not exist");
		}
		Card card = foundCard.get();
		//Retrieve the account linked to the card.
		Account linkedAccount = card.getAccount();


		//Perform the refund operation to the account.
		BigDecimal newBalance = linkedAccount.getBalance().add(refundAmount);
		linkedAccount.setBalance(newBalance);

		//create a transaction record for the refund.
		Transaction record = new Transaction(card,linkedAccount,refundAmount,OffsetDateTime.now());

		//save the updated account and transaction to the database
		accountRepository.save(linkedAccount);
		transactionRepository.save(record);

		//return charge amount and card type
		return new CardResult(card.getType(),refundAmount.doubleValue());
	}
	
	
	
	//**Transaction Lookup Methods**
	
	
	//Get all transactions
	//Method GET /transactions
	//Inputs: None
	//Outputs: All Transactions
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	
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
	
	
	//Get transactions by card and date range. Handle empty dates as wanting all.
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
	
	//Get transactions by account. Handle empty dates as wanting all.
	//Method POST /transactions/account
	//Inputs: Card Number, Start date, End date
	//Outputs: List of transactions within the range, for all cards and accounts
	public List<Transaction> getTransactionsByAccount(@RequestBody TransactionLookupRequest request){
		Integer accountNum = request.getAccountNum();
		OffsetDateTime beginTime = request.getBeginTimestamp();
		OffsetDateTime endTime = request.getEndTimestamp();
		Optional<List<Transaction>> results;
		
		
		//Check Inputs
		if(accountNum == null) {
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
			results = transactionRepository.findByAccountId(accountNum);
		}
		else {
			results = transactionRepository.findByAccountAndTimeBetween(accountNum, beginTime, endTime);
		}
		
		if(results.isEmpty()) {
			throw new NotFoundException(accountNum.toString());
		}
		
		return results.get();
		

	}
}
