package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    Optional<ProductType> findByProduct(Product product);
}
