package com.project.demo.projects.controllers;

import com.project.demo.projects.converters.ProjectConverter;
import com.project.demo.projects.models.ProjectDTO;
import com.project.demo.projects.models.ProjectFilter;
import com.project.demo.projects.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectConverter projectConverter;

    @PostMapping("/create")
    public String createProject(@RequestBody String name){
      return this.projectService.create(name);
    }
//    @PostMapping("/all-paged")
//    public Page<ProjectDTO> getAllProjectsPaged(@RequestBody ProjectFilter projectFilter, Pageable pageable){
//        return this.projectService.getAllProjectsPaged(projectFilter,pageable).map(this.projectConverter ::toProjectDTO);
//    }
}
