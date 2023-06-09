package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.dto.ProductSearchRequest;
import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Optional<Product> getProductById(Integer productId);
    Optional<Product> getProductByProductType(ProductType productType);
    Optional<Product> getProductByTechnicalDetail(TechnicalDetail technicalDetail);
    Optional<Product> getProductByDescription(Description description);
    List<Product> getAllProducts();
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsBySearchCriteria(ProductSearchRequest productSearchRequest);
    Product updateProduct(Product product);
    void deleteProduct(Integer productId);
}
