package com.openwt.task;

import com.openwt.task.models.Task;
import com.openwt.task.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void test_findByTitleOrDetail() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("TaskApi");
        task.setDetail("Ngay mai dealine");
        taskRepository.save(task);
        assertFalse(taskRepository.findByTitleContainsOrDetailContains("TaskApi","TaskApi").isEmpty());
        assertTrue(taskRepository.findByTitleContainsOrDetailContains("Abc","Abc").isEmpty());
    }
}
