package org.chapeullah.problemsolvingjournal.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.chapeullah.problemsolvingjournal.model.Task;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JsonMapper jsonMapper;
    private final Path file = Path.of("data/tasks.json");

    @PostConstruct
    public void createFile() throws IOException {
        Files.createDirectories(file.getParent());
        if (Files.notExists(file))
            Files.writeString(file, "[]");
        System.out.println("JSON-файл: " + file.toAbsolutePath());
    }

    public void deleteFile() throws IOException {
        Files.deleteIfExists(file);
    }

}
