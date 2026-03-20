package com.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Account {
    private int id;
    private String accountNumber;
    private BigDecimal accountBalance;

    public Account() {
    }

    public Account(int id, String accountNumber, BigDecimal accountBalance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
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

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}