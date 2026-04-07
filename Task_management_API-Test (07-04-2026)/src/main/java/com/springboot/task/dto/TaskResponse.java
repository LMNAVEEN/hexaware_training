package com.springboot.task.dto;

import com.springboot.task.model.Task;
import java.time.Instant;
import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        String priority,
        String status,
        Instant createdAt
) {
    public static TaskResponse fromEntity(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getDueDate(),
                t.getPriority().name(),
                t.getStatus().name(),
                t.getCreatedAt()
        );
    }
}