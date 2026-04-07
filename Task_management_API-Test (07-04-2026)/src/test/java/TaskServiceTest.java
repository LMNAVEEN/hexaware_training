package com.springboot.task.service;

import com.springboot.task.dto.TaskPageResDto;
import com.springboot.task.dto.TaskRequest;
import com.springboot.task.enums.Priority;
import com.springboot.task.enums.Status;
import com.springboot.task.exception.ResourceNotFoundException;
import com.springboot.task.model.Task;
import com.springboot.task.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    public void getTaskById_whenExists_returnsTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setPriority(Priority.HIGH);
        task.setStatus(Status.PENDING);
        task.setCreatedAt(Instant.now());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        Assertions.assertEquals("Test Task", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void getTaskById_whenNotFound_throwsException() {
        when(taskRepository.findById(10L)).thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.getTaskById(10L));

        Assertions.assertEquals("Task not found with id: 10", e.getMessage());
    }

    @Test
    public void addTask_savesAndReturnsTask() {
        TaskRequest request = new TaskRequest(
                "Task 1", "Desc", LocalDate.now(), Priority.HIGH, Status.PENDING
        );

        Task saved = new Task();
        saved.setTitle("Task 1");

        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        Task result = taskService.addTask(request);

        Assertions.assertEquals("Task 1", result.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void getAllTasks_returnsPagedContent() {
        Task t1 = new Task();
        t1.setId(1L);
        t1.setTitle("Task 1");

        Task t2 = new Task();
        t2.setId(2L);
        t2.setTitle("Task 2");

        Pageable pageable = PageRequest.of(0, 1);
        Page<Task> pageObj = new PageImpl<>(List.of(t1), pageable, 2);

        when(taskRepository.findAll(pageable)).thenReturn(pageObj);

        TaskPageResDto result = taskService.getAllTasks(0, 1);

        Assertions.assertEquals(1, result.content().size());
        Assertions.assertEquals("Task 1", result.content().get(0).getTitle());
    }

    @Test
    public void updateTask_whenExists_updatesAndReturnsTask() {
        Task existing = new Task();
        existing.setId(1L);
        existing.setTitle("Old Title");
        existing.setStatus(Status.PENDING);

        TaskRequest request = new TaskRequest(
                "New Title", "New Desc", LocalDate.now(), Priority.LOW, Status.IN_PROGRESS
        );

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        Task result = taskService.updateTask(1L, request);

        Assertions.assertEquals("New Title", result.getTitle());
        Assertions.assertEquals("New Desc", result.getDescription());
        Assertions.assertEquals(Priority.LOW, result.getPriority());
        Assertions.assertEquals(Status.IN_PROGRESS, result.getStatus());
        verify(taskRepository, times(1)).save(existing);
    }

    @Test
    public void updateTask_whenNotFound_throwsException() {
        TaskRequest request = new TaskRequest(
                "Title", "Desc", LocalDate.now(), Priority.MEDIUM, Status.PENDING
        );

        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.updateTask(99L, request));

        verify(taskRepository, never()).save(any());
    }

    @Test
    public void deleteTask_whenExists_deletesAndReturnsMessage() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        String result = taskService.deleteTask(1L);

        Assertions.assertEquals("Task deleted successfully", result);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    public void deleteTask_whenNotFound_throwsException() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.deleteTask(99L));

        verify(taskRepository, never()).delete(any());
    }
}