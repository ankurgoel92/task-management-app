package com.ag.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ag.persistence.model.Project;
import com.ag.persistence.model.Task;
import com.ag.persistence.repository.IProjectRepository;
import com.ag.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public Optional<Project> findById(long id) {
        LOG.info("Project Service >> Finding Project By Id {}", id);
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        LOG.info("Project Service >> Saving Project", project);
        return projectRepository.save(project);
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }
    
    @Override
    public Project addTasks(Project project, List<Task> tasks) {
        project.getTasks()
               .addAll(tasks.stream()
               .filter(t -> !StringUtils.isEmpty(t.getName()))
               .collect(Collectors.toList()));

        projectRepository.save(project);

        return project;
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
