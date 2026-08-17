package org.chapeullah.problemsolvingjournal.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.chapeullah.problemsolvingjournal.application.TaskService;
import org.chapeullah.problemsolvingjournal.dto.TaskRequest;
import org.chapeullah.problemsolvingjournal.dto.TaskResponse;
import org.chapeullah.problemsolvingjournal.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private static final Map<Task.Status, String> STATUS_LABELS = Map.of(
            Task.Status.NEW, "Новая",
            Task.Status.SOLVING, "Решаю",
            Task.Status.REPEAT, "Повторить",
            Task.Status.MASTERED, "Освоена",
            Task.Status.ABANDONED, "Отложена");

    private static final Map<Task.ProgrammingLanguage, String> LANGUAGE_LABELS = Map.of(
            Task.ProgrammingLanguage.JAVA, "Java",
            Task.ProgrammingLanguage.CPP, "C++",
            Task.ProgrammingLanguage.PYTHON, "Python",
            Task.ProgrammingLanguage.KOTLIN, "Kotlin",
            Task.ProgrammingLanguage.JAVASCRIPT, "JavaScript");

    private final TaskService taskService;

    @ModelAttribute("statuses")
    public Task.Status[] statuses() {
        return Task.Status.values();
    }

    @ModelAttribute("statusLabels")
    public Map<Task.Status, String> statusLabels() {
        return STATUS_LABELS;
    }

    @ModelAttribute("languages")
    public Task.ProgrammingLanguage[] languages() {
        return Task.ProgrammingLanguage.values();
    }

    @ModelAttribute("languageLabels")
    public Map<Task.ProgrammingLanguage, String> languageLabels() {
        return LANGUAGE_LABELS;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String getTasks(
            @PageableDefault(
                    size = 10,
                    sort = "updatedAt",
                    direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {
        model.addAttribute(
                "page",
                taskService.getTasks(pageable));
        return "tasks/list";
    }

    @GetMapping("/tasks/{id}")
    public String getTask(
            @PathVariable @Positive Long id,
            Model model) {
        model.addAttribute(
                "task",
                taskService.getTask(id));
        return "tasks/details";
    }

    @GetMapping("/tasks/new")
    public String getCreateForm(Model model) {
        TaskRequest request = new TaskRequest(
                "",
                null,
                null,
                null,
                Task.Status.NEW,
                new LinkedHashSet<>(Set.of("")),
                1,
                null);
        model.addAttribute("taskRequest", request);
        prepareForm(model, null);
        return "tasks/form";
    }

    @PostMapping("/tasks")
    public String createTask(
            @Valid
            @ModelAttribute("taskRequest")
            TaskRequest request,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, null);
            return "tasks/form";
        }
        TaskResponse created =
                taskService.createTask(request);
        return "redirect:/tasks/" + created.id();
    }

    @GetMapping("/tasks/{id}/edit")
    public String getEditForm(
            @PathVariable @Positive Long id,
            Model model) {
        TaskResponse task = taskService.getTask(id);
        TaskRequest request = new TaskRequest(
                task.title(),
                task.platform(),
                task.url(),
                task.language(),
                task.status(),
                task.topics(),
                task.confidence(),
                task.solvedAt());
        model.addAttribute("taskRequest", request);
        prepareForm(model, id);
        return "tasks/form";
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(
            @PathVariable @Positive Long id,
            @Valid
            @ModelAttribute("taskRequest")
            TaskRequest request,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, id);
            return "tasks/form";
        }
        taskService.updateTask(id, request);
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(
            @PathVariable @Positive Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    private void prepareForm(Model model, Long taskId) {
        boolean editing = taskId != null;

        model.addAttribute("editing", editing);
        model.addAttribute("taskId", taskId);
        model.addAttribute(
                "formTitle",
                editing ? "Редактирование задачи" : "Новая задача");
        model.addAttribute(
                "formAction",
                editing ? "/tasks/" + taskId + "/edit" : "/tasks");
        model.addAttribute(
                "submitLabel",
                editing ? "Сохранить изменения" : "Создать задачу");
        model.addAttribute(
                "cancelUrl",
                editing ? "/tasks/" + taskId : "/tasks");
    }
}
