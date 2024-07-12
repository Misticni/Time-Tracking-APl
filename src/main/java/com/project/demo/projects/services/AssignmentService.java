package com.project.demo.projects.services;

import com.project.demo.projects.exceptions.ProjectNotFoundException;
import com.project.demo.projects.models.*;
import com.project.demo.projects.repositories.AssignmentRepository;
import com.project.demo.projects.repositories.ProjectRepository;
import com.project.demo.users.models.User;
import com.project.demo.users.repositories.AssignedUserRepository;
import com.project.demo.users.services.LoggedUserService;
import com.project.demo.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final ProjectRepository projectRepository;
    private final AssignmentRepository assignmentRepository;
    private final LoggedUserService loggedUserService;
    private final UserService userService;
    private final AssignedUserRepository assignedUserRepository;
    public String createAssignment(Long projectId, String name){
        User loggedUser = this.loggedUserService.getLoggedUser();

        Project project = this.projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        Assignment assignment = new Assignment();
        assignment.setName(name);
        assignment.setCreator(loggedUser);
        assignment.setProject(project);
        assignment.setStartTime(OffsetDateTime.now());
        this.assignmentRepository.saveAndFlush(assignment);
        return "Assignments successfully created with id: " + assignment.getId();
    }

    public String assignUserToAssignment(Long assignmentId, Long userId) {
        User loggedUser = this.loggedUserService.getLoggedUser();
        if(loggedUser.getRoles().get(0).getRole().equals("ROLE_ADMIN")){
            Assignment assignment = this.assignmentRepository.findById(assignmentId).orElseThrow(ProjectNotFoundException::new);
            User user = this.userService.findById(userId);
            AssignedUser assignedUser = new AssignedUser();

            assignedUser.setAssignment(assignment);
            assignedUser.setAssigned(user);

            this.assignedUserRepository.saveAndFlush(assignedUser);
            return "Successful";
        }
        return "Failed";
    }

    @Transactional
    public String endAssignment(Long assignmentId) {
        User loggedUser = this.loggedUserService.getLoggedUser();
        Assignment assignment = this.assignmentRepository.findById(assignmentId).orElseThrow(ProjectNotFoundException::new);
        assignment.setEndTime(OffsetDateTime.now());
        this.assignmentRepository.saveAndFlush(assignment);

        AssignedUser assignedUser = this.assignedUserRepository.findByAssignment_Id(assignmentId);
        if(assignedUser.getAssigned().getId().equals(loggedUser.getId())) {
            this.assignedUserRepository.delete(assignedUser);
            return "Successfully ended assignment";
        }
        return "User is not assigned to that assignment";
    }
}

