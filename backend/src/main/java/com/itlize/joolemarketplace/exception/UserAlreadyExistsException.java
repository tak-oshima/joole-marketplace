package com.itlize.joolemarketplace.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userName) {
        super(String.format("A user with the username \"%s\" already exists.", userName));
    }
}
