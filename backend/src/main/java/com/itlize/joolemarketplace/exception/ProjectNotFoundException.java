package com.itlize.joolemarketplace.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Integer projectId) {
        super(String.format("A project with project_id \"%d\" could not be found", projectId));
    }
}
