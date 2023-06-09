package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Integer> {
    Optional<Description> findByProduct(Product product);
}
