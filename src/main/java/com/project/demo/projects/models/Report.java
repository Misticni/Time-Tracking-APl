package com.project.demo.projects.models;

import com.project.demo.common.models.BaseEntity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pr_report",schema = "projects")
public class Report extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_assignment_id")
    private Assignment assignment;

    @Column(name = "report_details")
    private String reportDetails;

}
