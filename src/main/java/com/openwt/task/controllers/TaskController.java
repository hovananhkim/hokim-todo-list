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
        return taskService.get(id);
    }

    @GetMapping
    Iterable<Task> get() {
        return taskService.get();
    }

    @PostMapping
    Task post(@RequestBody Task task) {
        return taskService.post(task);
    }

    @PutMapping("/{id}")
    Task put(@RequestBody Task task, @PathVariable int id) {
        return taskService.put(task, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        taskService.delete(id);
    }

    @GetMapping("/search")
    Iterable<Task> search(@RequestParam String keyword) {
        return taskService.search(keyword);
    }
}