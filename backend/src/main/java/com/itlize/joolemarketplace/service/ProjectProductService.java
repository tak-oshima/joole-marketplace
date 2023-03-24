package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;

import java.util.List;
import java.util.Optional;

public interface ProjectProductService {
    ProjectProduct createProjectProduct(ProjectProduct projectProduct);
    Optional<ProjectProduct> getProjectProductById(Integer projectProductId);
    List<ProjectProduct> getAllProjectProducts();
    List<ProjectProduct> getProjectProductsByProject(Project project);
    List<ProjectProduct> getProjectProductsByProduct(Product product);
    ProjectProduct updateProjectProduct(ProjectProduct projectProduct);
    void deleteProjectProduct(Integer projectProductId);
}
