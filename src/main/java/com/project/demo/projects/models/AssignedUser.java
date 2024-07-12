package com.project.demo.projects.models;

import com.project.demo.common.models.BaseEntity;
import com.project.demo.users.models.User;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pr_assigned_users",schema = "projects")
public class AssignedUser extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_user_u_id",nullable = false)
    private User assigned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pr_assignment_id",nullable = false)
    private Assignment assignment;
}
