package com.project.demo.projects.converters;

import com.project.demo.projects.models.ProjectDTO;
import com.project.demo.projects.models.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectConverter {
    public ProjectDTO toProjectDTO(Project project){
        return new ProjectDTO(
                project.getId(),
                project.getDateCreated(),
                project.getDateModified(),
                project.getName()
        );
    }
}
