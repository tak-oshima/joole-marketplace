package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.dto.ProductSearchRequest;
import com.itlize.joolemarketplace.exception.ProductNotFoundException;
import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.repository.DescriptionRepository;
import com.itlize.joolemarketplace.repository.ProductRepository;
import com.itlize.joolemarketplace.repository.ProductTypeRepository;
import com.itlize.joolemarketplace.repository.TechnicalDetailRepository;
import com.itlize.joolemarketplace.repository.specs.ProductTypeSpecs;
import com.itlize.joolemarketplace.repository.specs.TechnicalDetailSpecs;
import com.itlize.joolemarketplace.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final TechnicalDetailRepository technicalDetailRepository;
    private final DescriptionRepository descriptionRepository;

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
    public List<Product> getProductsBySearchCriteria(ProductSearchRequest productSearchRequest) {
        Specification<ProductType> productTypeSpec = ProductTypeSpecs.matchesSearchCriteria(productSearchRequest.getProductTypeSearchRequest());
        Set<Product> foundProductsByType = (productSearchRequest.getProductTypeSearchRequest() == null)
                ? new HashSet<>(productRepository.findAll())
                : productTypeRepository.findAll(productTypeSpec)
                        .stream()
                        .map(ProductType::getProduct)
                        .collect(Collectors.toSet());

        Specification<TechnicalDetail> technicalDetailSpec = TechnicalDetailSpecs.matchesSearchCriteria(productSearchRequest.getTechnicalDetailSearchRequest());
        Set<Product> foundProductsByTechnicalDetail = (productSearchRequest.getTechnicalDetailSearchRequest() == null)
                ? new HashSet<>(productRepository.findAll())
                : technicalDetailRepository.findAll(technicalDetailSpec)
                .stream()
                .map(TechnicalDetail::getProduct)
                .collect(Collectors.toSet());

        Set<Product> foundProductsByBrand = new HashSet<>(productRepository.findAllByProductBrand(productSearchRequest.getProductBrand()));

        return foundProductsByType.stream()
                .filter(foundProductsByTechnicalDetail::contains)
                .filter(foundProductsByBrand::contains)
                .collect(Collectors.toList());
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepository.findById(product.getProductId()).isPresent()) {
            throw new ProductNotFoundException(product.getProductId());
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (!productRepository.findById(productId).isPresent()) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }
}
