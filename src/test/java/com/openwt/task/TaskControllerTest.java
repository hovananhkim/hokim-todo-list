package com.openwt.task;

import com.openwt.task.exceptions.NotFoundException;
import com.openwt.task.models.Task;
import com.openwt.task.services.TaskService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class TaskControllerTest {
    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    private List<Task> tasks;
    private Task task1;

    @BeforeEach
    public void init() {
        tasks = new ArrayList<>();
        task1 = new Task(1, "Thu 2", "Di lam ca sang", false);
        tasks.add(task1);
    }

    @Test
    public void test_getAllTask() throws Exception {
        given(taskService.get()).willReturn(tasks);
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(task1.getId())))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo(task1.getTitle())))
                .andExpect(jsonPath("$[0].detail", Matchers.equalTo(task1.getDetail())));
    }

    @Test
    public void test_getTaskFound() throws Exception {
        given(taskService.get(1)).willReturn(task1);
        mockMvc.perform(get("/api/tasks/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(task1.getId())))
                .andExpect(jsonPath("$.title", Matchers.equalTo(task1.getTitle())))
                .andExpect(jsonPath("$.detail", Matchers.equalTo(task1.getDetail())));
    }

    @Test
    public void test_getTaskNotFound() throws Exception {
        when(taskService.get(5000)).thenThrow(new NotFoundException("Task 5000 not found"));
        mockMvc.perform(get("/api/tasks/{id}", 5000)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals("Task 5000 not found", result.getResolvedException().getMessage()));
    }

    @Test
    public void test_deleteTask_Found() throws Exception {
        mockMvc.perform(delete("/api/tasks/{id}", 2))
                .andExpect(status().isOk());
    }
}