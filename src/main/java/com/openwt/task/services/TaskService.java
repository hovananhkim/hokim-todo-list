package com.openwt.task.services;

import com.openwt.task.models.Task;

public interface TaskService {
    Task get(int id);

    Iterable<Task> get();

    void put(Task task);

    void post(Task task);

    void delete(int id);

    Iterable<Task> find(String title);
}
