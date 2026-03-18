package com.controller;

import com.config.ProjConfig;
import com.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class TicketController {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        TicketService ticketService = context.getBean(TicketService.class);

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1. Run Sample (AOP)");
            System.out.println("0. Exit");

            int input = sc.nextInt();

            if(input == 0){
                break;
            }

            switch(input){
                case 1:
                    ticketService.sample();
                    break;
            }
        }

        sc.close();
        context.close();
    }
}