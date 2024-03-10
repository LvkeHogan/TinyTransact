package com.lukehogan.tinytransact.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import jakarta.persistence.Column;

@Entity
@Table(name = "Transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    private int acctID;

    private double amount;

    @Column(name = "transacttime")
    private Timestamp time;

    //empty constructor needed for Hibernate/jpa to bring data into the application.
    public Transaction(){

    }

    //auxiliary constructor
    public Transaction(int acctID, double amount, Timestamp time){
        this.acctID = acctID;
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
