package com.ag.service;

import java.util.Optional;

import com.ag.persistence.model.Project;

public interface IProjectService {

    Optional<Project> findById(long id);

    Project save(Project project);
}
