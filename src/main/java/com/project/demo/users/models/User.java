package com.project.demo.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.demo.common.models.CreationAwareEntity;
import javax.persistence.*;

import com.project.demo.common.models.CreationAwareSoftDeletableEntity;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "u_user", schema = "users")
public class User extends CreationAwareSoftDeletableEntity {

    @Column(name = "email",nullable = false,unique = true)
    @Email
    @NotNull
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "enabled",nullable = false)
    @NotNull
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_person_id")
    private Person person;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @JsonIgnore
    public List<Role> getRoles(){
        if(userRoles == null){
            return new ArrayList<>();
        }
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
    }

}
