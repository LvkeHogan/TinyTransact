package com.lukehogan.tinytransact.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    @JoinColumn(name = "accountId", nullable = true)
    private Account account;

    private BigDecimal amount;
    
    @ManyToOne
    @JoinColumn(name = "cardId", nullable = true)
    private Card card;
    
    
    
    //to get current timestamp upon entity creation, OffsetDateTime.now(ZoneOffset.UTC)
    private OffsetDateTime time;

    //empty constructor needed for Hibernate/jpa to bring data into the application.
    public Transaction(){

    }

    //Constructor to allow bank deposit/withdraw
    public Transaction(Account account, BigDecimal amount, OffsetDateTime time){
        this.account = account;
        this.amount = amount;
        this.time = time;
    }
    
    
    //Constructor to allow card transactions
    public Transaction(Card card, BigDecimal amount, OffsetDateTime time){
        this.card = card;
        this.amount = amount;
        this.time = time;
    }
    
    //getters
    public BigDecimal getAmount(){
        return amount;
    }

    public OffsetDateTime getTime(){
        return time;
    }

    public Account getAccount() {
        return account;
    }

    public Card getCard() {
        return card;
    }

    //No setters defined; after a transaction is made, these values can't be changed.

}
