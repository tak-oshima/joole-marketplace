package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProductTypeNotFoundException;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.ProductTypeRepository;
import com.itlize.joolemarketplace.service.ProductTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

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
            throw new ProductTypeNotFoundException(productType.getProductTypeId());
        }
        return productTypeRepository.save(productType);
    }

    @Override
    public void deleteProductType(Integer productTypeId) {
        if (!productTypeRepository.findById(productTypeId).isPresent()) {
            throw new ProductTypeNotFoundException(productTypeId);
        }
        productTypeRepository.deleteById(productTypeId);
    }
}
