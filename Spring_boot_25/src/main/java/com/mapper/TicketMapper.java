package com.mapper;

import com.dto.TicketReqDto;
import com.dto.TicketResDto;
import com.model.Ticket;

public class TicketMapper {

    public static Ticket mapToEntity(TicketReqDto dto){
        Ticket ticket = new Ticket();
        ticket.setSubject(dto.subject());
        ticket.setDetails(dto.details());
        ticket.setPriority(dto.priority());
        return ticket;
    }
    public static TicketResDto mapToDto(Ticket ticket) {
        return TicketResDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .details(ticket.getDetails())
                .ticketPriority(ticket.getPriority())   // ✅ FIXED
                .ticketStatus(ticket.getStatus())       // ✅ FIXED
                .build(); // ❗ removed createdAt & updatedAt (not in entity)
    }
}