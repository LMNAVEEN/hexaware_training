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
    public void doTransfer(String fromAccountNumber,
                           String toAccountNumber, double amountToTransfer) {
        accountRepository.debit(fromAccountNumber,amountToTransfer);
        System.out.println("debit success...");
        //something happens
        if(true){
            throw new RuntimeException("Something went wrong!!!");
        }
        accountRepository.credit(toAccountNumber,amountToTransfer);
    }
}