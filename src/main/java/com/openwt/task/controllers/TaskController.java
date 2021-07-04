package com.openwt.task.controllers;

import com.openwt.task.models.Task;
import com.openwt.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/{id}")
    Task get(@PathVariable int id) {
        return taskService.findById(id);
    }

    @GetMapping
    Iterable<Task> get() {
        return taskService.findAll();
    }

    @PostMapping
    void post(@RequestBody Task task) {
        taskService.save(task);
    }

    @PutMapping
    void put(@RequestBody Task task) {
        taskService.save(task);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        taskService.delete(id);
    }

    @GetMapping("/find")
    Iterable<Task> find(@RequestParam String title) {
        return taskService.findByTitleContaining(title);
    }
}