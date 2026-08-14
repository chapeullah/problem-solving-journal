package org.chapeullah.problemsolvingjournal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Task {

    /**
     * NEW — not started;
     * SOLVING — in progress;
     * REPEAT — solved, needs review;
     * MASTERED — understood and reproducible;
     * ABANDONED — not currently being worked on.
     */
    public enum Status {
        NEW,
        SOLVING,
        REPEAT,
        MASTERED,
        ABANDONED
    }

    private Long id;
    private String title;
    private String platform;
    private String url;
    private Status status = Status.NEW;
    private Set<String> topics = new LinkedHashSet<>();
    private Integer confidence;
    private LocalDate solvedAt;
    private Instant createdAt;
    private Instant updatedAt;

    public Task(
            String title,
            String platform,
            String url,
            Status status,
            Set<String> topics,
            Integer confidence,
            LocalDate solvedAt) {
        this.title = title;
        this.platform = platform;
        this.url = url;
        this.status = status;
        this.confidence = confidence;
        this.solvedAt = solvedAt;

        setTopics(topics);
    }

    public Set<String> getTopics() {
        return Collections.unmodifiableSet(topics);
    }

    public void setTopics(Set<String> topics) {
        Set<String> copy = new LinkedHashSet<>(topics);
        this.topics.clear();
        this.topics.addAll(copy);
    }

}
