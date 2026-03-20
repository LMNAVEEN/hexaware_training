package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void debit(String fromAccountNumber, double amountToTransfer) {
        String sql="update account SET balance = balance - ? where account_number = ?";
        jdbcTemplate.update(sql,amountToTransfer,fromAccountNumber);
    }

    public void credit(String toAccountNumber, double amountToTransfer) {
        String sql="update account SET balance = balance + ? where account_number = ?";
        jdbcTemplate.update(sql,amountToTransfer,toAccountNumber);
    }
}