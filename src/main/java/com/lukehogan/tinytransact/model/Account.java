package com.lukehogan.tinytransact.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Integer accountNumber;

    private String firstName;

    private String lastName;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private Integer zipCode;
    
    private Long phoneNum;
    
    @OneToMany(mappedBy = "account")
    private List<Card> cards;
    
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    
    private BigDecimal balance;

    //empty constructor for Hibernate/JPA
    public Account() {

    }

    //auxiliary constructor
    public Account(String firstName, String lastName, double balance, List<Card> cards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = BigDecimal.valueOf(balance);
        this.cards = cards;
    }
    
    //Constructor for account creation
    public Account(String firstName, String lastName, String address, String city, String state, int zipCode, long phoneNum, int accountNumber) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.state = state;
    	this.zipCode = zipCode;
    	this.phoneNum = phoneNum;
    	this.accountNumber = accountNumber;
    	this.balance = new BigDecimal("0.00");
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public Integer getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getBalance() {
        return balance;
    }

    //setters: we are only handling changes to name and balance.
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }

	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
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

	public void setAddress(String address) {
		this.address = address;
	}
	
	//I don't like this setter, I don't want this to be changeable in the future. Might remove.
	//It only exists to support account creation.
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}


}
