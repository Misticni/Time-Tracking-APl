package com.project.demo.projects.models;

import com.project.demo.users.models.UserDTO;

import java.time.OffsetDateTime;
import java.util.List;

public record AssignmentDTO(
        Long id,
        String name,
        Long creatorId,
        List<UserDTO> assignedUserIds,
        OffsetDateTime started,
        Long projectId
) {
}
