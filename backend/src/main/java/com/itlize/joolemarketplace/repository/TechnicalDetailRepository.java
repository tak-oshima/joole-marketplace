package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalDetailRepository extends JpaRepository<TechnicalDetail, Integer> {
    Optional<TechnicalDetail> findByProduct(Product product);
}
