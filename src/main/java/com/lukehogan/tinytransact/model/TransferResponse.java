package com.lukehogan.tinytransact.model;

import org.springframework.stereotype.Component;


public class TransferResponse {
    private String From;
    private String To;
    private double amount;

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransferResponse(String from, String to, double amount) {
        From = from;
        To = to;
        this.amount = amount;
    }
}


