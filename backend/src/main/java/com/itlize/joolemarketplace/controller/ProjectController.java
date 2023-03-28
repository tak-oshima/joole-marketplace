package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.service.ProductService;
import com.itlize.joolemarketplace.service.ProjectService;

import com.itlize.joolemarketplace.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/create")
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
    public ResponseEntity<?> getProjectsByUserNameOrAll(@RequestParam(value = "userName", required = false) String userName) {
        Optional<User> foundUser = userService.getUserByUserName(userName);
        List<Project> projects;
        if (foundUser != null) {
            projects = projectService.getProjectsByUser(foundUser.get());
        }
        else {
            projects = projectService.getAllProjects();
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody List<Product> products) {
        try {
            for (Product product: products) {
                projectService.addProjectProducts(product.getProjectProducts());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Products already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/removeProducts")
    public ResponseEntity<?> removeProductsByProductId(@PathVariable List<Integer> productIds) {
        try {
            projectService.removeProjectProducts(productIds);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Products not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
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
