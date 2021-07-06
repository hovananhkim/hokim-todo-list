package com.openwt.task.services.impl;

import com.openwt.task.exceptions.NotFoundException;
import com.openwt.task.models.Task;
import com.openwt.task.repositories.TaskRepository;
import com.openwt.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task get(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new NotFoundException(String.format("Task %d not found", id));
    }

    @Override
    public Iterable<Task> get() {
        return taskRepository.findAll();
    }

    @Override
    public Task post(Task task) {

        task.setId(0);
        return taskRepository.save(task);
    }

    @Override
    public Task put(Task task, int id) {
        checkNotFound(id);
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public void delete(int id) {
        checkNotFound(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Iterable<Task> search(String keyword) {
        return taskRepository.findByTitleContainsOrDetailContains(keyword, keyword);
    }

    private void checkNotFound(int id) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException(String.format("Task %d not found", id));
        }
    }
}