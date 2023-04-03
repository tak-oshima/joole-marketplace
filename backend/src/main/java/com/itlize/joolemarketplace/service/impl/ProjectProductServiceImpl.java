package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProjectProductNotFoundException;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.repository.ProjectProductRepository;
import com.itlize.joolemarketplace.service.ProjectProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectProductServiceImpl implements ProjectProductService {
    private final ProjectProductRepository projectProductRepository;

    @Override
    public ProjectProduct createProjectProduct(ProjectProduct projectProduct) {
        return projectProductRepository.save(projectProduct);
    }

    @Override
    public Optional<ProjectProduct> getProjectProductById(Integer projectProductId) {
        return projectProductRepository.findById(projectProductId);
    }

    @Override
    public List<ProjectProduct> getAllProjectProducts() {
        return projectProductRepository.findAll();
    }

    @Override
    public List<ProjectProduct> getProjectProductsByProject(Project project) {
        return projectProductRepository.findAllByProject(project);
    }

    @Override
    public List<ProjectProduct> getProjectProductsByProduct(Product product) {
        return projectProductRepository.findAllByProduct(product);
    }

    @Override
    public ProjectProduct updateProjectProduct(ProjectProduct projectProduct) {
        if (!projectProductRepository.findById(projectProduct.getProjectProductId()).isPresent()) {
            throw new ProjectProductNotFoundException(projectProduct.getProjectProductId());
        }
        return projectProductRepository.save(projectProduct);
    }

    @Override
    public void deleteProjectProduct(Integer projectProductId) {
        if (!projectProductRepository.findById(projectProductId).isPresent()) {
            throw new ProjectProductNotFoundException(projectProductId);
        }
        projectProductRepository.deleteById(projectProductId);
    }
}
