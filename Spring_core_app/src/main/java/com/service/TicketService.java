package com.service;

import com.exception.TicketNotFoundException;
import com.models.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void insert(Ticket ticket) {
        ticketRepository.insert(ticket);
    }

    public void deleteTicket(int id) {

        int rows = ticketRepository.deleteById(id);

        if (rows == 0) {
            throw new TicketNotFoundException("Invalid ticket id");
        }
    }

    public List<Ticket> fetchAllTickets() {
        return ticketRepository.fetchAllTickets();
    }
}