package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.service.ProductService;
import com.itlize.joolemarketplace.service.ProductTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProductType(@RequestBody ProductType productType, Integer productId) {
        try{
            ProductType createdProductType = productTypeService.createProductType(productType);
            Product product = productService.getProductById(productId).get();
            product.setProductType(createdProductType);
            return new ResponseEntity<>(createdProductType, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProductType already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getProductTypeById(@RequestParam(value = "productTypeId", required = false) Integer productTypeId) {
        Optional<ProductType> foundProductType = productTypeService.getProductTypeById(productTypeId);
        if (foundProductType.isPresent()) {
            return new ResponseEntity<>(foundProductType.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProductType not found");
            responseBody.put("details", String.format("ProductType with id \"%s\" could not be found", productTypeId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getProductTypeByProduct(@RequestParam(value = "productId", required = false) Integer productId) {
        Product product = productService.getProductById(productId).get();
        Optional<ProductType> foundProductType = productTypeService.getProductTypeByProduct(product);
        if (foundProductType.isPresent()) {
            return new ResponseEntity<>(foundProductType.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProductType not found");
            responseBody.put("details", String.format("ProductType with product \"%s\" could not be found", product));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProductType(@RequestBody ProductType productType, Integer productId) {
        try {
            ProductType updatedProductType = productTypeService.updateProductType(productType);
            Product product = productService.getProductById(productId).get();
            product.setProductType(updatedProductType);
            return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProductType not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductTypeBy(@PathVariable Integer productId) {
        try {
            Optional<Product> product = productService.getProductById(productId);
            productTypeService.deleteProductType(product.get().getProductType().getProductTypeId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "ProductType not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
