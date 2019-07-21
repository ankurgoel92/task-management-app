package com.ag.persistence.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ag.persistence.model.Project;
import com.ag.persistence.repository.IProjectRepository;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository {
    
    private static final Logger LOG = LoggerFactory.getLogger(ProjectRepositoryImpl.class);

    @Value("${project.prefix}")
    private String prefix;

    @Value("${project.suffix}")
    private int suffix;
    
    private List<Project> projects = new ArrayList<Project>();

    @Override
    public Optional<Project> findById(long id) {
        return projects.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public Project save(Project project) {
        Project existingProject = findById(project.getId()).orElse(null);
        if (existingProject == null) {
            updateInternalId(project);
            projects.add(project);
        } else {
            projects.remove(existingProject);
            Project newProject = new Project(project);
            updateInternalId(newProject);
            projects.add(newProject);
        }

        return project;
    }
    
    private void updateInternalId(Project project) {
        LOG.info("Prepending Prefix " + prefix);
        LOG.info("Appending Suffix " + suffix);

        project.setInternalId(prefix + "-" + project.getId() + "-" + suffix);

        LOG.info("Generated internal id " + project.getInternalId());
    }

}
