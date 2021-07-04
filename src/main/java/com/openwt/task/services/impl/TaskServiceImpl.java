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
    public Task findById(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new NotFoundException(String.format("Task %d not found", id));
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(int id) {
        if (!taskRepository.existsById(id)){
            throw new NotFoundException(String.format("Task %d not found", id));
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Iterable<Task> findByTitleContaining(String title) {
        return taskRepository.findByTitleContaining(title);
    }
}