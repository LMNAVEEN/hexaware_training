package com.springboot.task.dto;

import com.springboot.task.enums.Priority;
import com.springboot.task.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Due date is required")
        LocalDate dueDate,

        @NotNull(message = "Priority is required")
        Priority priority,

        @NotNull(message = "Status is required")
        Status status
) {}