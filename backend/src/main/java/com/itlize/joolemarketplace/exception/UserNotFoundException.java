package com.itlize.joolemarketplace.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName) {
        super(String.format("User with user_name \"%s\" could not be found", userName));
    }
}
