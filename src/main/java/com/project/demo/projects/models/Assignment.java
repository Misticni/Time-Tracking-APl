package com.project.demo.projects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.common.models.CreationAwareEntity;
import com.project.demo.users.models.User;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "pr_assignment", schema = "projects")
public class Assignment extends CreationAwareEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_project_id")
    private Project project;

    @Column(name="start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_user_u_id")
    private User creator;

    @JsonIgnore
    @OneToMany(mappedBy = "assignment",fetch = FetchType.EAGER)
    private List<AssignedUser> assignedUsers;

    @JsonIgnore
    public List<User> getAssignedUsers(){
        if(assignedUsers == null){
            return new ArrayList<>();
        }
        return assignedUsers.stream().map(AssignedUser::getAssigned).collect(Collectors.toList());
    }
}
