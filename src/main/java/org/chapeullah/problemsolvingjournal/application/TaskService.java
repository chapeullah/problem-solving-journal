package org.chapeullah.problemsolvingjournal.application;

import lombok.RequiredArgsConstructor;
import org.chapeullah.problemsolvingjournal.dto.TaskRequest;
import org.chapeullah.problemsolvingjournal.dto.TaskResponse;
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
    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task(
                request.title(),
                request.platform(),
                request.url(),
                request.language(),
                request.status(),
                request.topics(),
                request.confidence(),
                request.solvedAt());
        return TaskResponse.from(
                taskRepository.saveAndFlush(task));
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(task -> TaskResponse.from(task));
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long id) {
        return TaskResponse.from(findTask(id));
    }

    @Transactional
    public TaskResponse updateTask(
            Long id,
            TaskRequest request) {
        Task task = findTask(id);

        task.setTitle(request.title());
        task.setPlatform(request.platform());
        task.setUrl(request.url());
        task.setLanguage(request.language());
        task.setStatus(request.status());
        task.setTopics(request.topics());
        task.setConfidence(request.confidence());
        task.setSolvedAt(request.solvedAt());

        return TaskResponse.from(taskRepository.saveAndFlush(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.delete(findTask(id));
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
