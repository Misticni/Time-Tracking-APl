package com.project.demo.projects.models;

import com.project.demo.common.models.CreationAwareEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pr_project", schema = "projects")
public class Project extends CreationAwareEntity {
    @Column(name = "name")
    private String name;
}
