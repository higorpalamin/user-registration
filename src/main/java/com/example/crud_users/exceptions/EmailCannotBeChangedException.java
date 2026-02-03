package com.example.crud_users.exceptions;

public class EmailCannotBeChangedException extends RuntimeException {

    public EmailCannotBeChangedException() {
        super("Email cannot be changed, please try again.");
    }

    public EmailCannotBeChangedException(String message) {
        super(message);
    }
}
