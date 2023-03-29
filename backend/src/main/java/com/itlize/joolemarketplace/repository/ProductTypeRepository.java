package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>, JpaSpecificationExecutor<ProductType> {
    Optional<ProductType> findByProduct(Product product);
}
