package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectProductRepository extends JpaRepository<ProjectProduct, Integer> {
    List<ProjectProduct> findAllByProject(Project project);
    List<ProjectProduct> findAllByProduct(Product product);
}
