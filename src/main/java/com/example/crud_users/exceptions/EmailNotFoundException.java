package com.example.crud_users.exceptions;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException() {
        super("Email not found in database");
    }

    public EmailNotFoundException(String message) {
        super(message);
    }


}
