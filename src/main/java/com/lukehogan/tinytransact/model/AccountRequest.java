package com.lukehogan.tinytransact.model;

import org.springframework.stereotype.Component;

@Component
public class AccountRequest {
	private int accountNumber;
	private String firstName;
	private String lastName;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public AccountRequest(int accountNumber, String firstName, String lastName) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
}
