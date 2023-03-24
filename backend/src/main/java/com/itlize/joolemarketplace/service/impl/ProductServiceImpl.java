package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.repository.ProductRepository;
import com.itlize.joolemarketplace.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Optional<Product> getProductByProductType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    @Override
    public Optional<Product> getProductByTechnicalDetail(TechnicalDetail technicalDetail) {
        return productRepository.findByTechnicalDetail(technicalDetail);
    }

    @Override
    public Optional<Product> getProductByDescription(Description description) {
        return productRepository.findByDescription(description);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByBrand(String productBrand) {
        return productRepository.findAllByProductBrand(productBrand);
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepository.findById(product.getProductId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update Product Exception: product with product_id \"%d\" not found", product.getProductId())
            );
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (!productRepository.findById(productId).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete Product Exception: product with product_id \"%d\" not found", productId)
            );
        }
        productRepository.deleteById(productId);
    }
}
