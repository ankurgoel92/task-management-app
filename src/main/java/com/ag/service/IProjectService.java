package com.ag.service;

import java.util.List;
import java.util.Optional;

import com.ag.persistence.model.Project;
import com.ag.persistence.model.Task;

public interface IProjectService {

    Optional<Project> findById(long id);

    Project save(Project project);

    Iterable<Project> findAll();

    Project addTasks(Project project, List<Task> collect);
}
