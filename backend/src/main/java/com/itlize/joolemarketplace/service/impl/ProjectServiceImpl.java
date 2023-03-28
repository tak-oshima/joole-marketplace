package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProjectNotFoundException;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.ProductRepository;
import com.itlize.joolemarketplace.repository.ProjectProductRepository;
import com.itlize.joolemarketplace.repository.ProjectRepository;
import com.itlize.joolemarketplace.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectProductRepository projectProductRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findAllByUser(user);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void addProjectProducts(List<ProjectProduct> projectProducts) {
        projectProductRepository.saveAll(projectProducts);
    }

    @Override
    public void removeProjectProducts(List<Integer> projectProductIds) {
        projectProductRepository.deleteAllByIdInBatch(projectProductIds);
    }

    @Override
    public Project updateProject(Project project) {
        if (!projectRepository.findById(project.getProjectId()).isPresent()) {
            throw new ProjectNotFoundException(project.getProjectId());
        }
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer projectId) {
        if (!projectRepository.findById(projectId).isPresent()) {
            throw new ProjectNotFoundException(projectId);
        }
        projectRepository.deleteById(projectId);
    }
}
