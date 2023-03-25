package com.itlize.joolemarketplace.exception;

public class DescriptionNotFoundException extends RuntimeException{
    public DescriptionNotFoundException(Integer descriptionId) {
        super(String.format("A description with description_id \"%d\" could not be found", descriptionId));
    }
}
