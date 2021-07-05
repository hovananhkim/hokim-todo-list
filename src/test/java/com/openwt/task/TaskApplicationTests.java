package com.openwt.task;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class TaskApplicationTests {
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Test
    void contextLoads() {
    }

    @Test
    public void test_dataSourceUrl() {
        assertEquals(dataSourceUrl, "jdbc:mysql://localhost:3306/task_test");
    }
}
