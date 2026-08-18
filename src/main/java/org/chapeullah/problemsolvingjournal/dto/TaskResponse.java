package org.chapeullah.problemsolvingjournal.dto;

import org.chapeullah.problemsolvingjournal.model.Task;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public record TaskResponse(
        Long id,
        String title,
        String platform,
        String url,
        String language,
        Task.Status status,
        Set<String> topics,
        Integer confidence,
        LocalDate solvedAt,
        Instant createdAt,
        Instant updatedAt) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getPlatform(),
                task.getUrl(),
                normalizeLegacyLanguage(task.getLanguage()),
                task.getStatus(),
                Set.copyOf(task.getTopics()),
                task.getConfidence(),
                task.getSolvedAt(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }

    private static String normalizeLegacyLanguage(String language) {
        if (language == null)
            return null;

        return switch (language) {
            case "JAVA" -> "Java";
            case "CPP" -> "C++";
            case "PYTHON" -> "Python";
            case "KOTLIN" -> "Kotlin";
            case "JAVASCRIPT" -> "JavaScript";
            default -> language;
        };
    }
}
