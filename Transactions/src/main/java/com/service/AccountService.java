package com.service;

import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void doTransfer(String fromAccount,
                           String toAccount,
                           double amount) {

        accountRepository.debit(fromAccount, amount);
        System.out.println("Debit success...");

        accountRepository.credit(toAccount, amount);
        System.out.println("Credit success...");
    }
}