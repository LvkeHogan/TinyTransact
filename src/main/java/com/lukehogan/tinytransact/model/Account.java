package com.lukehogan.tinytransact.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="Accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custID;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "acctnum")
    private Long acctNum;

    private double balance;

    //empty constructor needed for Hibernate/jpa to bring data into the application.
    public Account() {

    }

    //auxiliary constructor
    public Account(String firstName, String lastName, Long acctNum, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.acctNum = acctNum;
        this.balance = balance;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getAcctNum() {
        return acctNum;
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


}
