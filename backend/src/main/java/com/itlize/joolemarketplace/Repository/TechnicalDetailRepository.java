package com.itlize.joolemarketplace.Repository;

import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalDetailRepository extends JpaRepository<TechnicalDetail, Integer> {

    List<TechnicalDetail> findByProduct(Product product);
}