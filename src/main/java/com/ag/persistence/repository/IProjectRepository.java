package com.ag.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ag.persistence.model.Project;

public interface IProjectRepository extends CrudRepository<Project, Long> {
    
    Optional<Project> findByName(String name);
    
    List<Project> findByDateCreatedBetween(LocalDate fromDate, LocalDate toDate);

}
