package org.chapeullah.problemsolvingjournal.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
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

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "platform", length = 256)
    private String platform;

    @Column(name = "url", length = 8196)
    private String url;

    @Column(name = "language", length = 64)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private Status status = Status.NEW;

    @ElementCollection
    @CollectionTable(
            name = "task_topics",
            joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "topic", nullable = false, length = 100)
    private Set<String> topics = new LinkedHashSet<>();

    @Column(name = "confidence", nullable = false)
    private Integer confidence;

    @Column(name = "solved_at")
    private LocalDate solvedAt;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Task(
            String title,
            String platform,
            String url,
            String language,
            Status status,
            Set<String> topics,
            Integer confidence,
            LocalDate solvedAt) {
        this.title = title;
        this.platform = platform;
        this.url = url;
        setLanguage(language);
        this.status = status;
        this.confidence = confidence;
        this.solvedAt = solvedAt;

        setTopics(topics);
    }

    public Set<String> getTopics() {
        return Collections.unmodifiableSet(topics);
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public void setTopics(Set<String> topics) {
        Set<String> copy = new LinkedHashSet<>(topics);
        this.topics.clear();
        this.topics.addAll(copy);
    }

}
