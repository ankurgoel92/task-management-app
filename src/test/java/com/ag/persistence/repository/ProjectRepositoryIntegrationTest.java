package com.ag.persistence.repository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ag.persistence.model.Project;

@SpringBootTest
class ProjectRepositoryIntegrationTest {

    @Autowired
    private IProjectRepository projectRepository;

    @Test
    void testSave() {
        final Project savedProject = projectRepository.save(new Project(randomAlphabetic(6), LocalDate.now()));
        assertNotNull(projectRepository.save(savedProject));
    }

    @Test
    public void givenProject_whenFindById_thenSuccess() {
        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        Optional<Project> retreivedProject = projectRepository.findById(newProject.getId());

        assertEquals(retreivedProject.get(), newProject);
    }

    @Test
    public void givenProject_whenFindByName_thenSuccess() {
        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        Optional<Project> retreivedProject = projectRepository.findByName(newProject.getName());

        assertEquals(retreivedProject.get(), newProject);
    }

    @Test
    public void givenProjects_whenFindByDateCreatedBetween_thenSuccess() {
        Project oldProject = new Project(randomAlphabetic(6), LocalDate.now().minusYears(1));
        projectRepository.save(oldProject);

        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        Project newProject2 = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject2);

        List<Project> retreivedProjects = projectRepository.findByDateCreatedBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        
        assertThat(retreivedProjects, hasItems(newProject, newProject2));
    }

}