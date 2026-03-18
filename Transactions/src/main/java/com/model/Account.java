package com.model;

import java.math.BigDecimal;

public class Account {

    private int id;
    private String accountNumber;
    private BigDecimal balance;

    public Account() {}

    public Account(int id, String accountNumber, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}