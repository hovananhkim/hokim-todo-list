package com.openwt.task.services;

import com.openwt.task.models.Task;

import java.util.Optional;

public interface TaskService {
    Task findById(int id);
    Iterable<Task> findAll();
    void save(Task task);
    void delete(int id);
    Iterable<Task> findByTitleContaining(String title);
}
