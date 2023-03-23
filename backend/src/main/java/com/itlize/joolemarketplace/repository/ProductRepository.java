package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductType(ProductType productType);
    Optional<Product> findByTechnicalDetail(TechnicalDetail technicalDetail);
    Optional<Product> findByDescription(Description description);
    List<Product> findAllByProductBrand(String productBrand);
}
