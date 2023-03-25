package com.itlize.joolemarketplace.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName) {
        super(String.format("A user with the username \"%s\" could not be found.", userName));
    }
}
