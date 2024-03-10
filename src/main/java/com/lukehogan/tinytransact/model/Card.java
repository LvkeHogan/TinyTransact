package com.lukehogan.tinytransact.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cards")
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardID;

    private int acctID;

    private String type;

    @Column(name = "cardnum")
    private long cardNum;

    //Empty jpa constructor
    public Card(){

    }

    //auxiliary constructor
    public Card(int cardID, int acctID, String type, long cardNum){
        this.cardID = cardID;
        this.acctID = acctID;
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

    //Setters: Only card number change supported (i.e. when a card gets stolen and you need to issue a new one)

    public void setCardNum(long newCardNum){
        this.cardNum = newCardNum;
    }

}
