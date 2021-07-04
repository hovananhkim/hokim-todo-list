package com.openwt.task.controllers;

import com.openwt.task.models.Task;
import com.openwt.task.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/{id}")
    Optional<Task> get(@PathVariable int id) {
        return taskRepository.findById(id);
    }

    @GetMapping
    Iterable<Task> get() {
        return taskRepository.findAll();
    }

    @PostMapping
    void post(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping
    void put(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/find")
    Iterable<Task> find(@RequestParam String title) {
        return taskRepository.findByTitleContaining(title);
    }
}