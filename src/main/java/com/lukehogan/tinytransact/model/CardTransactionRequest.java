package com.lukehogan.tinytransact.model;

public class CardTransactionRequest {
    private Double amount;
    private Long cardNum;

    public CardTransactionRequest(Double amount, Long accountNum) {
        this.amount = amount;
        this.cardNum = accountNum;
    }

    //Getters
    public Double getAmount() {
        return amount;
    }

    public Long getCardNum() {
        return cardNum;
    }
}

