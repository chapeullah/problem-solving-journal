package org.chapeullah.problemsolvingjournal.application;

import lombok.RequiredArgsConstructor;
import org.chapeullah.problemsolvingjournal.dto.CreateTaskRequest;
import org.chapeullah.problemsolvingjournal.dto.TaskResponse;
import org.chapeullah.problemsolvingjournal.dto.UpdateTaskRequest;
import org.chapeullah.problemsolvingjournal.exception.TaskNotFoundException;
import org.chapeullah.problemsolvingjournal.model.Task;
import org.chapeullah.problemsolvingjournal.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task(
                request.title(),
                request.platform(),
                request.url(),
                request.status(),
                request.topics(),
                request.confidence(),
                request.solvedAt()
        );

        return null;
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasks(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long id) {
        return null;
    }

    @Transactional
    public TaskResponse updateTask(
            Long id,
            UpdateTaskRequest request
    ) {
        return null;
    }

    @Transactional
    public void deleteTask(Long id) {
    }
}