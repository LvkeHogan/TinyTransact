package com.lukehogan.tinytransact.model;

public class AccountTransactionRequest {
    private Double amount;
    private Integer accountNum;

    public AccountTransactionRequest(Double amount, Integer accountNum) {
        this.amount = amount;
        this.accountNum = accountNum;
    }

    //Getters
    public Double getAmount() {
        return amount;
    }

    public Integer getAccountNum() {
        return accountNum;
    }
}

