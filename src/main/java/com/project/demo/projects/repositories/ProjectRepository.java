package com.project.demo.projects.repositories;

import com.project.demo.projects.models.Project;
import com.project.demo.projects.models.ProjectFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p where " +
            ":#{#projectFilter.name()==null} = true or lower(p.name) like %:#{#projectFilter.name()}% ")
    Page<Project> findAllFilteredPaged(ProjectFilter projectFilter, Pageable pageable);

}
