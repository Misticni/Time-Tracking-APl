package com.project.demo.users.repositories;

import com.project.demo.projects.models.AssignedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignedUserRepository extends JpaRepository<AssignedUser,Long> {
    AssignedUser findByAssignment_Id(Long id);
}
