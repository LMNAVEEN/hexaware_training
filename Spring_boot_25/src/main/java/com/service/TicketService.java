package com.service;

import com.dto.TicketPageResDto;
import com.dto.TicketReqDto;
import com.dto.TicketResDto;
import com.exception.TicketNotFoundException;
import com.mapper.TicketMapper;
import com.model.Ticket;
import com.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket addTicket(TicketReqDto dto) {
        Ticket ticket = TicketMapper.mapToEntity(dto);
        ticket.setStatus("OPEN");
        return ticketRepository.save(ticket);
    }

    public TicketPageResDto getAllTickets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ticket> pageTicket = ticketRepository.findAll(pageable);

        long totalRecords = pageTicket.getTotalElements();
        long totalPages = pageTicket.getTotalPages();

        List<TicketResDto> listDto = pageTicket
                .toList()
                .stream()
                .map(TicketMapper::mapToDto)
                .toList();

        return new TicketPageResDto(
                listDto,
                totalRecords,
                totalPages
        );
    }

    public TicketResDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        return TicketMapper.mapToDto(ticket);
    }
}