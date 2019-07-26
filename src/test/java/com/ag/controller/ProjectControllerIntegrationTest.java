package com.ag.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenProjectExists_thenGetSuccess() throws Exception {
        mockMvc.perform(get("/rest/projects/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Project 1"));
    }

}
