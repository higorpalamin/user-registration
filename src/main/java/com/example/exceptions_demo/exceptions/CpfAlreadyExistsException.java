package com.example.exceptions_demo.exceptions;

public class CpfAlreadyExistsException extends RuntimeException {

    public CpfAlreadyExistsException() {
        super("CPF already exists, please try again.");
    }

    public CpfAlreadyExistsException(String message) {
        super(message);
    }
}
