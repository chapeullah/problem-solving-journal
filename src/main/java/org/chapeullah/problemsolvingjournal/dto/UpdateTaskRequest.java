package org.chapeullah.problemsolvingjournal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.chapeullah.problemsolvingjournal.model.Task;

import java.time.LocalDate;
import java.util.Set;

public record UpdateTaskRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 256, message = "Title must not exceed 256 characters")
        String title,

        @Size(max = 255, message = "Platform must not exceed 255 characters")
        String platform,

        @Size(max = 8196, message = "URL must not exceed 8196 characters")
        String url,

        @NotNull(message = "Status is required")
        Task.Status status,

        @NotEmpty(message = "At least one topic is required")
        Set<
                @NotBlank(message = "Topic must not be blank")
                @Size(max = 100, message = "Topic must not exceed 100 characters")
                        String
                > topics,

        @NotNull(message = "Confidence is required")
        @Min(value = 1, message = "Confidence must be at least 1")
        @Max(value = 5, message = "Confidence must not exceed 5")
        Integer confidence,

        @PastOrPresent(message = "Solved date cannot be in the future")
        LocalDate solvedAt

) {}