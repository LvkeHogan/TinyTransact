package com.lukehogan.tinytransact.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    private double amount;
    
    // TODO add card relationship onetoone
    
    // TODO what data type should we use for this?
    private Timestamp time;

    //empty constructor needed for Hibernate/jpa to bring data into the application.
    public Transaction(){

    }

    //auxiliary constructor
    public Transaction(Account account, double amount, Timestamp time){
        this.account = account;
        this.amount = amount;
        this.time = time;
    }

    //getters
    public double getAmount(){
        return amount;
    }

    public Timestamp getTime(){
        return time;
    }

    //setters
    public void setAmount(double newAmount){
        this.amount = newAmount;
    }

    public void setTime(Timestamp newTime){
        this.time = newTime;
    }

}
