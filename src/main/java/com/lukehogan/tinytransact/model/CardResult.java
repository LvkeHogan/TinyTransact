package com.lukehogan.tinytransact.model;

public class CardResult {
    private String cardType;
    private Double amount;

    public CardResult(String cardType, Double amount) {
        this.cardType = cardType;
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
