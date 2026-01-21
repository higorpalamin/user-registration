package com.example.exceptions_demo.exceptions;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException() {
        super("Email not found in database");
    }

    public EmailNotFoundException(String message) {
        super(message);
    }


}
