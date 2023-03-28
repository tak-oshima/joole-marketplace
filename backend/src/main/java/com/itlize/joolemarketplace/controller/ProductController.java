package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.dto.ProductSearchCriteriaDto;
import com.itlize.joolemarketplace.model.*;
import com.itlize.joolemarketplace.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectProductService projectProductService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    TechnicalDetailService technicalDetailService;

    @Autowired
    DescriptionService descriptionService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Unable to create product");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{productId}/product-type")
    public ResponseEntity<?> addProductTypeToProduct(@PathVariable Integer productId, @RequestBody ProductType productType) {
        Optional<Product> foundProduct = productService.getProductById(productId);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            productType.setProduct(product);
            product.setProductType(productType);
            ProductType createdProductType = productTypeService.createProductType(productType);
            return new ResponseEntity<>(createdProductType, HttpStatus.CREATED);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", String.format("Product with product_id %d could not be found", productId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{productId}/technical-detail")
    public ResponseEntity<?> addTechnicalDetailToProduct(@PathVariable Integer productId, @RequestBody TechnicalDetail technicalDetail) {
        Optional<Product> foundProduct = productService.getProductById(productId);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            technicalDetail.setProduct(product);
            product.setTechnicalDetail(technicalDetail);
            TechnicalDetail createdTechnicalDetail = technicalDetailService.createTechnicalDetail(technicalDetail);
            return new ResponseEntity<>(createdTechnicalDetail, HttpStatus.CREATED);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", String.format("Product with product_id %d could not be found", productId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{productId}/description")
    public ResponseEntity<?> addDescriptionToProduct(@PathVariable Integer productId, @RequestBody Description description) {
        Optional<Product> foundProduct = productService.getProductById(productId);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            description.setProduct(product);
            product.setDescription(description);
            Description createdDescription = descriptionService.createDescription(description);
            return new ResponseEntity<>(createdDescription, HttpStatus.CREATED);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", String.format("Product with product_id %d could not be found", productId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        Optional<Product> foundProduct = productService.getProductById(productId);
        if (foundProduct.isPresent()) {
            return new ResponseEntity<>(foundProduct.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", String.format("Product with project_id %d could not be found", productId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getProductsByProjectId(@PathVariable Integer projectId) {
        Optional<Project> foundProject = projectService.getProjectById(projectId);
        if (foundProject.isPresent()) {
            List<Product> products = projectProductService.getProjectProductsByProject(foundProject.get())
                    .stream()
                    .map(ProjectProduct::getProduct)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Project not found");
            responseBody.put("details", String.format("Project with project_id %d could not be found", projectId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsBySearchCriteria(@RequestBody ProductSearchCriteriaDto productSearchCriteriaDto) {
        List<Product> foundProducts = productService.getProductsBySearchCriteria(productSearchCriteriaDto);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/brand/{productBrand}")
    public ResponseEntity<?> getProductsByBrand(@PathVariable String productBrand) {
        List<Product> products = productService.getProductsByBrand(productBrand);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Product not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
