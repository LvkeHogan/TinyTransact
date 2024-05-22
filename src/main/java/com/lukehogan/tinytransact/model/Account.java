package com.lukehogan.tinytransact.model;

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
    
    private int accountNumber;

    private String firstName;

    private String lastName;
    
    @OneToMany(mappedBy = "account")
    private List<Card> cards;
    
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    
    private double balance;

    //empty constructor for Hibernate/JPA
    public Account() {

    }

    //auxiliary constructor
    public Account(String firstName, String lastName, double balance, List<Card> cards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.cards = cards;
    }
    
    //Constructor for account creation
    public Account(String firstName, String lastName, int accountNumber) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.accountNumber = accountNumber;
    	this.balance = 0.00;
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

    public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
        return balance;
    }

    //setters: we are only handling changes to name and balance.
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


}
