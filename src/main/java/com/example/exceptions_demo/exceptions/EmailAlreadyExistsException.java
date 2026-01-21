package com.example.exceptions_demo.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists, please try again.");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
