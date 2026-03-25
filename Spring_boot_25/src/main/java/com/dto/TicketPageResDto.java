package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TicketPageResDto {
    private List<TicketResDto> tickets;
    private long totalRecords;
    private long totalPages;    // ← must be long, not int
}