package com.project.demo.projects.repositories;

import com.project.demo.projects.models.Assignment;
import com.project.demo.projects.models.AssignmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
