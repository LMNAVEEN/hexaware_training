package com.controller;

import com.dto.TicketPageResDto;
import com.dto.TicketReqDto;
import com.dto.TicketResDto;
import com.model.Ticket;
import com.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto dto) {
        Ticket saved = ticketService.addTicket(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", saved.getId()));
    }

    @GetMapping("/get-all")
    public ResponseEntity<TicketPageResDto> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ticketService.getAllTickets(page, size));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TicketResDto> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
}