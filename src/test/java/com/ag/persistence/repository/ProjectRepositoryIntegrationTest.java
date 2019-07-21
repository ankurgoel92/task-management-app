package com.ag.persistence.repository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

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

    @Test
    public void givenProjects_whenFindAllPaginated_thenSuccess() {
        Page<Project> retreivedProjects = projectRepository.findAll(PageRequest.of(0, 2));

        assertThat(retreivedProjects.getContent(), hasSize(2));
    }

    @Test
    public void givenProjects_whenFindAllSorted_thenSuccess() {

        List<Project> retreivedProjects = (List<Project>) projectRepository.findAll(Sort.by(Order.asc("name")));

        List<Project> projectList = retreivedProjects.stream().collect(Collectors.toList());
        projectList.sort(Comparator.comparing(Project::getName));

        assertEquals(retreivedProjects, projectList);

    }
    
    @Test
    public void givenProjects_whenFindPaginatedAndSortedProjects_thenSuccess() {
        Page<Project> retreivedProjects = projectRepository.findAll(PageRequest.of(0, 2, Sort.by(Order.asc("name"))));
        
        assertThat(retreivedProjects.getContent(), hasSize(2));
    }

}
