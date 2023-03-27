package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project createdProject = projectService.createProject(project);
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProjectId already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer projectId) {
        Optional<Project> foundProject = projectService.getProjectById(projectId);
        if (foundProject.isPresent()) {
            return new ResponseEntity<>(foundProject.get() , HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Project not found");
            responseBody.put("details", String.format("Project with project_id \"%d\" could not be found", projectId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getProjectsByUserOrAll(@RequestParam(value = "user", required = false) User user) {
        List<Project> projects;
        if (user != null) {
            projects = projectService.getProjectsByUser(user);
        }
        else {
            projects = projectService.getAllProjects();
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        try {
            Project updatedProject = projectService.updateProject(project);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Project not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Integer projectId) {
        try {
            projectService.deleteProject(projectId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Project not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }


}
