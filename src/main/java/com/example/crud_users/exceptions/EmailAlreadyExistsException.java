package com.example.crud_users.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists, please try again.");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
