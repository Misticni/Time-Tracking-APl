package com.project.demo.projects.controllers;

import com.project.demo.projects.converters.AssignmentConverter;
import com.project.demo.projects.models.AssignmentDTO;
import com.project.demo.projects.models.AssignmentFilter;
import com.project.demo.projects.models.ProjectDTO;
import com.project.demo.projects.services.AssignmentService;
import com.project.demo.projects.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignmentConverter assignmentConverter;

    @PostMapping("/create/{projectId}")
    public String createAssignment(@PathVariable Long projectId, @RequestBody String name){
        return this.assignmentService.createAssignment(projectId,name);
    }
    @PostMapping("/assign/{assignmentId}/{userId}")
    public String assignUserToAssignment(@PathVariable Long assignmentId, @PathVariable Long userId){
        return this.assignmentService.assignUserToAssignment(assignmentId,userId);
    }
    @PostMapping("/end/{assignmentId}")
    public String endAssignment(@PathVariable Long assignmentId){
        return this.assignmentService.endAssignment(assignmentId);
    }
}
