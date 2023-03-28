package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.service.ProductService;
import com.itlize.joolemarketplace.service.TechnicalDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/technicalDetails")
public class TechnicalDetailController {
    @Autowired
    TechnicalDetailService technicalDetailService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createTechnicalDetail(@RequestBody TechnicalDetail technicalDetail, Integer productId) {
        try{
            TechnicalDetail createdTechnicalDetail = technicalDetailService.createTechnicalDetail(technicalDetail);
            Product product = productService.getProductById(productId).get();
            product.setTechnicalDetail(createdTechnicalDetail);
            return new ResponseEntity<>(createdTechnicalDetail, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "TechnicalDetail already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getTechnicalDetailById(@RequestParam(value = "technicalDetailId", required = false) Integer technicalDetailId) {
        Optional<TechnicalDetail> foundTechnicalDetail = technicalDetailService.getTechnicalDetailById(technicalDetailId);
        if (foundTechnicalDetail.isPresent()) {
            return new ResponseEntity<>(foundTechnicalDetail.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "TechnicalDetail not found");
            responseBody.put("details", String.format("TechnicalDetail with id \"%s\" could not be found", technicalDetailId));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getTechnicalDetailByProduct(@RequestParam(value = "productId", required = false) Integer productId) {
        Product product = productService.getProductById(productId).get();
        Optional<TechnicalDetail> foundTechnicalDetail = technicalDetailService.getTechnicalDetailByProduct(product);
        if (foundTechnicalDetail.isPresent()) {
            return new ResponseEntity<>(foundTechnicalDetail.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "TechnicalDetail not found");
            responseBody.put("details", String.format("TechnicalDetail with product \"%s\" could not be found", product));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTechnicalDetail(@RequestBody TechnicalDetail technicalDetail, Integer productId) {
        try {
            TechnicalDetail updatedTechnicalDetail = technicalDetailService.updateTechnicalDetail(technicalDetail);
            Product product = productService.getProductById(productId).get();
            product.setTechnicalDetail(updatedTechnicalDetail);
            return new ResponseEntity<>(updatedTechnicalDetail, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "TechnicalDetail not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteTechnicalDetailBy(@PathVariable Integer productId) {
        try {
            Optional<Product> product = productService.getProductById(productId);
            technicalDetailService.deleteTechnicalDetail(product.get().getTechnicalDetail().getTechnicalDetailId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "TechnicalDetail not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}