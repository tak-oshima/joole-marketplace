package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    ProductType createProductType(ProductType productType);
    Optional<ProductType> getProductTypeById(Integer productTypeId);
    Optional<ProductType> getProductTypeByProduct(Product product);
    List<ProductType> getAllProductTypes();
    ProductType updateProductType(ProductType productType);
    void deleteProductType(Integer productTypeId);
}
