package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void debit(String fromAccountNumber, double amount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
        int rows = jdbcTemplate.update(sql, amount, fromAccountNumber);

        if (rows == 0) {
            throw new RuntimeException("Debit failed: Account not found");
        }
    }

    public void credit(String toAccountNumber, double amount) {
        String sql = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
        int rows = jdbcTemplate.update(sql, amount, toAccountNumber);

        if (rows == 0) {
            throw new RuntimeException("Credit failed: Account not found");
        }
    }
}