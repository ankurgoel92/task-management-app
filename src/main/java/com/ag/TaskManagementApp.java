package com.ag;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ag.persistence.model.Project;
import com.ag.service.IProjectService;

@SpringBootApplication
public class TaskManagementApp {

    @Autowired
    private IProjectService projectService;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApp.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        Project project = new Project(1, "My First Project", LocalDate.now());
        projectService.save(project);
    }
}
