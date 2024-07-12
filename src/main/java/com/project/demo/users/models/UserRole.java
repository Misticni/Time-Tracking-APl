package com.project.demo.users.models;

import com.project.demo.common.models.BaseEntity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "u_user_role", schema = "users")
public class UserRole extends BaseEntity {

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "u_user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="u_role_id", nullable = false)
    private Role role;
}
