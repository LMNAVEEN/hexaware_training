package com.springboot.task.dto;

import com.springboot.task.model.Task;
import java.util.List;

public record TaskPageResDto(
        List<Task> content
) {}