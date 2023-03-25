package com.itlize.joolemarketplace.exception;

public class ProjectProductNotFoundException extends RuntimeException {
    public ProjectProductNotFoundException(Integer projectProductId) {
        super(String.format("A project product with project_product_id \"%d\" could not be found", projectProductId));
    }
}
