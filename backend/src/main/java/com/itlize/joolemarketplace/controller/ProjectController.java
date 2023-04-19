package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.dto.ProjectCreateRequest;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.service.ProductService;
import com.itlize.joolemarketplace.service.ProjectProductService;
import com.itlize.joolemarketplace.service.ProjectService;
import com.itlize.joolemarketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectProductService projectProductService;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateRequest request) {
        Optional<User> foundUser = userService.getUserByUserName(request.getUsername());
        if (foundUser.isPresent()) {
            Project project = Project.builder()
                    .projectName(request.getProjectName())
                    .user(foundUser.get())
                    .build();
            Project createdProject = projectService.createProject(project);
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", String.format("User with user_name \"%s\" could not be found", request.getUsername()));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getProjectsByUsername(@PathVariable String username) {
        Optional<User> foundUser = userService.getUserByUserName(username);
        if (foundUser.isPresent()) {
            List<Project> foundProjects = projectService.getProjectsByUser(foundUser.get());
            return new ResponseEntity<>(foundProjects, HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", String.format("User with user_name \"%s\" could not be found", username));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{projectId}/add-product/{productId}")
    public ResponseEntity<?> addProductToProject(@PathVariable Integer projectId, @PathVariable Integer productId) {
        Optional<Project> foundProject = projectService.getProjectById(projectId);
        if (foundProject.isPresent()) {
            Optional<Product> foundProduct = productService.getProductById(productId);
            if (foundProduct.isPresent()) {
                ProjectProduct projectProduct = ProjectProduct.builder()
                        .project(foundProject.get())
                        .product(foundProduct.get())
                        .build();
                ProjectProduct createdProjectProduct = projectProductService.createProjectProduct(projectProduct);
                return new ResponseEntity<>(createdProjectProduct, HttpStatus.CREATED);
            }
            else {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("error", "Product not found");
                responseBody.put("details", String.format("Product with product_id \"%d\" could not be found", productId));
                return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            }
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Project not found");
            responseBody.put("details", String.format("Project with project_id \"%d\" could not be found", projectId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
