package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getProjectById(Integer projectId);
    List<Project> getProjectsByUser(User user);
    List<Project> getAllProjects();
    void addProductsToProject(Integer projectId, List<Integer> productIds);
    void removeProductsFromProject(List<Integer> projectProductIds);
    Project updateProject(Project project);
    void deleteProject(Integer projectId);
}
