package com.lukehogan.tinytransact.model;

import org.springframework.stereotype.Component;

public class AccountRequest {
	private Integer accountNumber;
	private String firstName;
	private String lastName;
	private String address;
    private String city;
    private String state;
    private Integer zipCode;
    private Long phoneNum;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public Long getPhoneNum() {
		return phoneNum;
	}
	
	//Constructor for simple lookup operations
	public AccountRequest(Integer accountNumber, String firstName, String lastName) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//Constructor for account update operations (we could probably consolidate down to this one only since this class can accept null values.
	public AccountRequest(Integer accountNumber, String firstName, String lastName, String address, String city,
			String state, Integer zipCode, Long phoneNum) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNum = phoneNum;
	}
	public AccountRequest(){
	}

	
	
	
	
	
}
