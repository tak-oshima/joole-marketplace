package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.service.DescriptionService;
import com.itlize.joolemarketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/descriptions")
public class DescriptionController {
    @Autowired
    DescriptionService descriptionService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createDescription(@RequestBody Description description) {
        try {
            Description createdDescription = descriptionService.createDescription(description);
            return new ResponseEntity<>(createdDescription, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Description already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getDescriptionByProduct(@RequestParam(value = "product", required = false) Product product) {
        Optional<Description> foundDescription = descriptionService.getDescriptionByProduct(product);
        if (foundDescription.isPresent()) {
            return new ResponseEntity<>(foundDescription.get(), HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Description not found");
            responseBody.put("details", String.format("Description with product \"%s\" could not be found", product));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping
    public ResponseEntity<?> getAllDescriptions() {
        List<Description> descriptions = descriptionService.getAllDescriptions();
        return new ResponseEntity<>(descriptions, HttpStatus.OK);
    }**/

    @PutMapping
    public ResponseEntity<?> updateDescription(@RequestBody Description description) {
        try {
            Description updatedDescription = descriptionService.updateDescription(description);
            return new ResponseEntity<>(updatedDescription, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Description not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteDescriptionBy(@PathVariable Integer productId) {
        try {
            Optional<Product> product = productService.getProductById(productId);
            descriptionService.deleteDescription(product.get().getDescription().getDescriptionId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Description not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
