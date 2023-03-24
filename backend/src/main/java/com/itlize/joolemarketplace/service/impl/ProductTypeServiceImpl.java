package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.ProductTypeRepository;
import com.itlize.joolemarketplace.service.ProductTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductType createProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public Optional<ProductType> getProductTypeById(Integer productTypeId) {
        return productTypeRepository.findById(productTypeId);
    }

    @Override
    public Optional<ProductType> getProductTypeByProduct(Product product) {
        return productTypeRepository.findByProduct(product);
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType updateProductType(ProductType productType) {
        if (!productTypeRepository.findById(productType.getProductTypeId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update ProductType Exception: productType with productType_id \"%d\" not found", productType.getProductTypeId())
            );
        }
        return productTypeRepository.save(productType);
    }

    @Override
    public void deleteProductType(Integer productTypeId) {
        if (!productTypeRepository.findById(productTypeId).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete ProductType Exception: productType with productType_id \"%d\" not found", productTypeId)
            );
        }
        productTypeRepository.deleteById(productTypeId);
    }
}
