package org.chapeullah.problemsolvingjournal.repository;

import org.chapeullah.problemsolvingjournal.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
