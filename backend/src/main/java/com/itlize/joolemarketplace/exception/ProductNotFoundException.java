package com.itlize.joolemarketplace.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer productId) {
        super(String.format("A product with product_id \"%d\" could not be found", productId));
    }
}
