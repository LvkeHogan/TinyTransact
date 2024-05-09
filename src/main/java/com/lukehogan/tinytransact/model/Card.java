package com.lukehogan.tinytransact.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cards")
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    
    @OneToMany(mappedBy = "card")
    private List<Transaction> transactions;

    private String type;

    private long cardNum;

    //Empty constructor for JPA
    public Card(){

    }

    //auxiliary constructor
    public Card(int cardID, Account account, String type, long cardNum){
        this.id = cardID;
        this.account = account;
        this.type = type;
        this.cardNum = cardNum;
    }

    //getters

    public String getType() {
        return type;
    }

    public long getCardNum() {
        return cardNum;
    }

    public Account getAccount() {
		return account;
	}


}
