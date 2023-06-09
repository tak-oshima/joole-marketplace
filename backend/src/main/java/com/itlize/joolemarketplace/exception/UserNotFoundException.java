package com.itlize.joolemarketplace.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super(String.format("User with user_name \"%s\" could not be found", username));
    }
}
