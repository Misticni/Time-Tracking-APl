package com.project.demo.projects.converters;

import com.project.demo.projects.models.Assignment;
import com.project.demo.projects.models.AssignmentDTO;
import com.project.demo.users.converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentConverter {
    private final UserConverter userConverter;
    public AssignmentDTO toAssignmentDTO(Assignment assignment){
        return new AssignmentDTO(
                assignment.getId(),
                assignment.getName(),
                assignment.getCreator() != null ? assignment.getCreator().getId() : null,
                this.userConverter.toUserDTOList(assignment.getAssignedUsers()),
                assignment.getStartTime(),
                assignment.getProject() != null ? assignment.getProject().getId() : null
        );
    }
}
