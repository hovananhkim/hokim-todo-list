package com.openwt.task;

import com.openwt.task.models.Task;
import com.openwt.task.repositories.TaskRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class TaskControllerTest {
    @Autowired
    private TaskRepository taskRepository;

    private Task task1 = new Task(5, "Thu 2", "Di lam ca sang");
    private Task task2 = new Task(6, "Thu 3", "Di lam ca chieu");

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
}
