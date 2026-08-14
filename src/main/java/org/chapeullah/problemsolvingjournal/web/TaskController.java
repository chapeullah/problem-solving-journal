package org.chapeullah.problemsolvingjournal.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.chapeullah.problemsolvingjournal.application.TaskService;
import org.chapeullah.problemsolvingjournal.dto.CreateTaskRequest;
import org.chapeullah.problemsolvingjournal.dto.TaskResponse;
import org.chapeullah.problemsolvingjournal.dto.UpdateTaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskResponse> getTasks(Pageable pageable) {
        return taskService.getTasks(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTask(
            @PathVariable @Positive Long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(
            @PathVariable @Positive Long id,
            @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
            @PathVariable @Positive Long id) {
        taskService.deleteTask(id);
    }
}