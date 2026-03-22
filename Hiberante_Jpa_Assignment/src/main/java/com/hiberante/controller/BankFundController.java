package com.hiberante.controller;

import com.hiberante.config.ProjConfig;
import com.hiberante.model.Fund;
import com.hiberante.model.Manager;
import com.hiberante.service.BankFundService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.List;
import java.util.Scanner;

public class BankFundController {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        LocalContainerEntityManagerFactoryBean emf = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        BankFundService bankFundService = context.getBean(BankFundService.class);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Bank Fund Manager ===");
            System.out.println("1. Insert Manager");
            System.out.println("2. Insert Fund");
            System.out.println("3. Fetch all Funds");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int input = sc.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    Manager manager = new Manager();
                    System.out.print("Enter the Name: ");
                    manager.setName(sc.next());
                    System.out.print("Enter the Email: ");
                    manager.setEmail(sc.next());
                    bankFundService.insert(manager);
                    System.out.println("✔ Manager added successfully (id=" + manager.getId() + ")");
                    break;

                case 2:
                    System.out.print("Enter the manager id: ");
                    int managerId = sc.nextInt();
                    try {
                        Manager mgr = bankFundService.getManager(managerId);
                        Fund fund = new Fund();
                        System.out.print("Enter fund Name: ");
                        fund.setName(sc.next());
                        System.out.print("Enter AUM Amount: ");
                        fund.setAumAmount(sc.nextBigDecimal());
                        System.out.print("Enter Expense Ratio: ");
                        fund.setExpenseRatio(sc.nextBigDecimal());
                        System.out.print("Enter time of acc creation (any string, e.g. 2024-01-01): ");
                        String createdAt = sc.next();
                        bankFundService.insertintoFund(fund, createdAt, mgr);
                        System.out.println("✔ Fund added successfully");
                    } catch (RuntimeException e) {
                        System.out.println("✘ Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    List<?> list = bankFundService.fetchAllFundsForManager();
                    if (list.isEmpty()) {
                        System.out.println("No funds found.");
                    } else {
                        list.forEach(System.out::println);
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();
        context.close();
        System.out.println("Goodbye!");
    }
}
