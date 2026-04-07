package com.springboot.task.dto;

public record UserResponse(
        Long id,
        String username,
        String role
) {}