package com.service;

import com.exception.TicketNotFoundException;
import com.model.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void insert(Ticket ticket) {
        ticketRepository.insert(ticket);
    }

    public void deleteTicket(int id) {
        int updatedRows = ticketRepository.deleteById(id);
        if(updatedRows == 0)
            throw new TicketNotFoundException("ticket Id given is invalid..");
    }

    public List<Ticket> fetchAllTickets() {
        return ticketRepository.fetchAllTickets();
    }

    public void sample() {
        ticketRepository.sample();
    }
}