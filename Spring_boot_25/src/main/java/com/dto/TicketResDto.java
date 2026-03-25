package com.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResDto {

    private Long id;
    private String subject;
    private String details;
    private String ticketPriority;
    private String ticketStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}