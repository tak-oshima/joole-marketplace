package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TechnicalDetailRepository extends JpaRepository<TechnicalDetail, Integer>, JpaSpecificationExecutor<TechnicalDetail> {
    Optional<TechnicalDetail> findByProduct(Product product);
}
