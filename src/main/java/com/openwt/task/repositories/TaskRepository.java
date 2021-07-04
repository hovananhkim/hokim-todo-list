package com.openwt.task.repositories;

import com.openwt.task.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Iterable<Task> findByTitleContaining(String title);
}
