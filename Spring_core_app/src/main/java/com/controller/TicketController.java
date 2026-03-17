package com.controller;

import com.config.ProjConfig;
import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.exception.TicketNotFoundException;
import com.models.Ticket;
import com.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class TicketController {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        Scanner sc = new Scanner(System.in);

        TicketService ticketService = context.getBean(TicketService.class);
        Ticket ticket = context.getBean(Ticket.class);

        ticket.setSubject("Internet working slow");
        ticket.setDetails("Some sites load v slowly esp after 6PM");
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setTicketPriority(TicketPriority.LOW);
        ticket.setCreatedAt(Instant.now());

        ticketService.insert(ticket);
        System.out.println("Ticket Record Inserted in DB");

        while (true) {

            System.out.println("\n1. Insert");
            System.out.println("2. Delete by id");
            System.out.println("3. Show all Tickets");
            System.out.println("0. Exit");

            int input = sc.nextInt();

            if (input == 0)
                break;

            switch (input) {

                case 1:
                    ticket.setSubject("Internet working slow");
                    ticket.setDetails("Some sites load v slowly esp after 6PM");
                    ticket.setTicketStatus(TicketStatus.OPEN);
                    ticket.setTicketPriority(TicketPriority.LOW);
                    ticket.setCreatedAt(Instant.now());

                    ticketService.insert(ticket);
                    System.out.println("Ticket Record Inserted in DB");
                    break;

                case 2:
                    System.out.println("Enter ID to delete");
                    int id = sc.nextInt();

                    try {
                        ticketService.deleteTicket(id);
                        System.out.println("Ticket deleted..");
                    } catch (TicketNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    List<Ticket> list = ticketService.fetchAllTickets();
                    list.forEach(System.out::println);
                    break;
            }
        }

        sc.close();
    }
}