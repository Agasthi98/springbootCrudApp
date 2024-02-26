package com.TodoApp.springbootCrudApp;

import com.TodoApp.springbootCrudApp.model.LogController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import static org.hamcrest.Matchers.*;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class IntegrationTest {

    // creating a logger
    Logger logger
            = LoggerFactory.getLogger(LogController.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllTodos() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Get all list success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.sent_requests", is(notNullValue())));

        logger.info("Get all list success");

    }


}
