package com.itlize.joolemarketplace.exception;

public class ProductTypeNotFoundException extends RuntimeException{
    public ProductTypeNotFoundException(Integer productTypeId) {
        super(String.format("A product type with product_type_id \"%d\" could not be found", productTypeId));
    }
}
