package com.controller;

import com.config.ProjConfig;
import com.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountController {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        AccountService accountService = context.getBean(AccountService.class);

        try {
            accountService.doTransfer("ABC", "XYZ", 500);
            System.out.println("Transaction Success!!!!");
        } catch (Exception e) {
            e.printStackTrace(); // VERY IMPORTANT
            System.out.println("Transaction Failed");
        }

        context.close();
    }
}