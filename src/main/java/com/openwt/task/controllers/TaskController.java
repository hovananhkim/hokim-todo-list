package com.openwt.task.controllers;

import com.openwt.task.models.Task;
import com.openwt.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public Task get(@PathVariable int id) {
        return taskService.get(id);
    }

    @GetMapping
    public Iterable<Task> get() {
        return taskService.get();
    }

    @PostMapping
    public Task post(@Valid @RequestBody Task task) {
        return taskService.post(task);
    }

    @PutMapping("/{id}")
    public Task put(@RequestBody Task task, @PathVariable int id) {
        return taskService.put(task, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        taskService.delete(id);
    }

    @GetMapping("/search")
    public Iterable<Task> search(@RequestParam String keyword) {
        return taskService.search(keyword);
    }
}