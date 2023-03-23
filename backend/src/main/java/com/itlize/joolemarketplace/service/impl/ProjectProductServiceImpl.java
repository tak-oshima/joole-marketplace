package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.repository.ProjectProductRepository;
import com.itlize.joolemarketplace.service.ProjectProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProjectProductServiceImpl implements ProjectProductService {
    @Autowired
    private ProjectProductRepository projectProductRepository;

    @Override
    public ProjectProduct createProjectProduct(ProjectProduct projectProduct) {
        if (projectProductRepository.findById(projectProduct.getProjectProductId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Create ProjectProduct Exception: project_product with project_product_id \"%d\" already exists", projectProduct.getProjectProductId())
            );
        }
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
            throw new RuntimeException(
                    String.format("Create ProjectProduct Exception: project_product with project_product_id \"%d\" already exists", projectProduct.getProjectProductId())
            );
        }
        return projectProductRepository.save(projectProduct);
    }

    @Override
    public void deleteProjectProduct(ProjectProduct projectProduct) {
        if (!projectProductRepository.findById(projectProduct.getProjectProductId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Create ProjectProduct Exception: project_product with project_product_id \"%d\" already exists", projectProduct.getProjectProductId())
            );
        }
        projectProductRepository.delete(projectProduct);
    }
}
