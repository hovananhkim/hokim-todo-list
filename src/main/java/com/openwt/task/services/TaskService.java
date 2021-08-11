package com.openwt.task.services;

import com.openwt.task.models.Task;

public interface TaskService {
    Task get(int id);

    Iterable<Task> get();

    Task put(Task task, int id);

    Task post(Task task);

    void delete(int id);

    Iterable<Task> search(String keyword);
}
