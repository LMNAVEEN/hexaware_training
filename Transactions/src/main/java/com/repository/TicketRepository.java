package com.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    public void sample(){
        try {
            System.out.println("Executing sample method...");
            Thread.sleep(3000); // simulate delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}