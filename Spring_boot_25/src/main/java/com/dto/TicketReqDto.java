package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TicketReqDto(

        @NotBlank(message = "subject cannot be blank")
        @Size(min = 3, max = 255, message = "subject size must be 3-255")
        String subject,

        @NotBlank(message = "details cannot be blank")
        String details,

        String priority
) {}