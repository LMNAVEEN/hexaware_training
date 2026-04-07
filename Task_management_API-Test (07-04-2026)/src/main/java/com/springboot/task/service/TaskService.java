package com.springboot.task.service;

import com.springboot.task.dto.TaskRequest;
import com.springboot.task.exception.ResourceNotFoundException;
import com.springboot.task.model.Task;
import com.springboot.task.repository.TaskRepository;
import lombok.AllArgsConstructor;
import com.springboot.task.dto.TaskPageResDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task addTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(request.priority());
        task.setStatus(request.status());

        return taskRepository.save(task);
    }

    public TaskPageResDto getAllTasks(int page, int size) {

        return new TaskPageResDto(
                taskRepository.findAll(PageRequest.of(page, size)).getContent()
        );
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, TaskRequest request) {
        Task task = getTaskById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(request.priority());
        task.setStatus(request.status());

        return taskRepository.save(task);
    }

    public String deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
        return "Task deleted successfully";
    }
}