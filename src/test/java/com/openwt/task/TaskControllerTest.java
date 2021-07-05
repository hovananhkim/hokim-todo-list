package com.openwt.task;

import com.openwt.task.models.Task;
import com.openwt.task.repositories.TaskRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class TaskControllerTest {
    @Autowired
    private TaskRepository taskRepository;

    private Task task2 = new Task(2, "Thu 4", "Di lam ca sang", false);
    private Task task1 = new Task(1, "Thu 3", "Di lam ca chieu", false);

    @Before
    public void init() {
        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @Autowired
    private MockMvc mockMvc;

    @After
    public void destroy() {
        taskRepository.deleteAll();
    }

    @Test
    public void getAllTask() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(task1.getId())))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo(task1.getTitle())))
                .andExpect(jsonPath("$[0].detail", Matchers.equalTo(task1.getDetail())))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(task2.getId())))
                .andExpect(jsonPath("$[1].title", Matchers.equalTo(task2.getTitle())))
                .andExpect(jsonPath("$[1].detail", Matchers.equalTo(task2.getDetail())));
    }

    @Test
    public void test_getTask_Found() throws Exception {
        mockMvc.perform(get("/api/tasks/" + task1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(task1.getId())))
                .andExpect(jsonPath("$.title", Matchers.equalTo(task1.getTitle())))
                .andExpect(jsonPath("$.detail", Matchers.equalTo(task1.getDetail())));
    }

    @Test
    public void test_getTask_NotFound() throws Exception {
        mockMvc.perform(get("/api/tasks/5000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deleteTask_NotFound() throws Exception {
        mockMvc.perform(delete("/api/tasks/5000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deleteTask_Found() throws Exception {
        mockMvc.perform(delete("/api/tasks/" + task2.getId()))
                .andExpect(status().isOk());
        assertFalse(taskRepository.findById(task2.getId()).isPresent());
    }

}
