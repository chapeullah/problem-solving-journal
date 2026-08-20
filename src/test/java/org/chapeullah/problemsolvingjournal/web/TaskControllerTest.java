package org.chapeullah.problemsolvingjournal.web;

import org.chapeullah.problemsolvingjournal.application.TaskService;
import org.chapeullah.problemsolvingjournal.dto.TaskResponse;
import org.chapeullah.problemsolvingjournal.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void editFormShowsPreviouslySavedSolvedDate() throws Exception {
        TaskResponse task = new TaskResponse(
                7L,
                "Two Sum",
                "LeetCode",
                "https://leetcode.com/problems/two-sum",
                "Java",
                Task.Status.REPEAT,
                Set.of("arrays"),
                4,
                LocalDate.of(2026, 8, 19),
                Instant.parse("2026-08-19T10:00:00Z"),
                Instant.parse("2026-08-19T10:30:00Z"));
        when(taskService.getTask(7L)).thenReturn(task);

        mockMvc.perform(get("/tasks/7/edit")
                        .locale(Locale.forLanguageTag("ru-RU")))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/form"))
                .andExpect(content().string(containsString(
                        "name=\"solvedAt\" value=\"2026-08-19\"")));
    }
}
