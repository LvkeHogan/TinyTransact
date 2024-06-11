package com.lukehogan.tinytransact.model;

import org.springframework.stereotype.Component;

public class CardRequest {
    private Long cardNumber;
    private String type;
    private Integer accountNumber;

    public CardRequest(String cardType, Long cardNumber, Integer accountNumber) {
        this.type = cardType;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public String getType() {
        return type;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
}
