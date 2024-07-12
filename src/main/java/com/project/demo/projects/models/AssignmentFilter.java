package com.project.demo.projects.models;

import java.time.OffsetDateTime;
import java.util.List;

public record AssignmentFilter(
        Long id,
        String name,
        Long creatorId,
        List<Long> assignedUserIds,
        OffsetDateTime started,
        Long projectId
) {
}
