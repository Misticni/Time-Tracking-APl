package com.project.demo.projects.models;

import java.time.OffsetDateTime;

public record ProjectDTO(
        Long id,
        OffsetDateTime dateCreated,
        OffsetDateTime dateModified,
        String name
) {
}
