package com.ag.persistence.repository;

import java.util.Optional;

import com.ag.persistence.model.Project;

public interface IProjectRepository {
    Optional<Project> findById(long id);

    Project save(Project project);

}
