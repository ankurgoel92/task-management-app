package com.ag.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.ag.persistence.model.Project;

public interface IProjectRepository extends CrudRepository<Project, Long>{

}
