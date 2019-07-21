package com.ag.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ag.config.TestConfig;
import com.ag.persistence.model.Project;

@SpringJUnitConfig(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ProjectServiceIntegrationTest {

    @Autowired
    private IProjectService projectService;
    
    @Test
    public void testSave() {
        Project savedProject = projectService.save(new Project("My First Project", LocalDate.now()));
        assertThat(savedProject, is(notNullValue()));
    }
}
