package com.project.demo.users.models;

import com.project.demo.common.models.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "u_role", schema = "users")
public class Role extends BaseEntity {
    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @Column(name = "details")
    private String details;

}
