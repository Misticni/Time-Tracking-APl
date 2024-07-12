package com.project.demo.projects.services;

import com.project.demo.projects.models.ProjectFilter;
import com.project.demo.projects.repositories.ProjectRepository;
import com.project.demo.projects.models.Project;
import com.project.demo.users.exceptions.UserHasNoReadPrivilegeException;
import com.project.demo.users.models.User;
import com.project.demo.users.services.LoggedUserService;
import com.project.demo.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    private final UserService userService;
    private final LoggedUserService loggedUserService;

    @Transactional
    public String create(String name){
        User loggedUser = loggedUserService.getLoggedUser();
        if(!loggedUser.getRoles().get(0).getRole().equals("ROLE_ADMIN")){
            throw new UserHasNoReadPrivilegeException();
        }

        Project project = new Project();
        project.setName(name);
        this.projectRepository.saveAndFlush(project);
        return "Successfully created new project: " + name;
    }

}
