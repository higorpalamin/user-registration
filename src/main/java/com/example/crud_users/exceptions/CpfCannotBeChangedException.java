package com.example.crud_users.exceptions;

public class CpfCannotBeChangedException extends RuntimeException {

    public CpfCannotBeChangedException() {
        super("CPF cannot be changed, please try again.");
    }

    public CpfCannotBeChangedException(String message) {
        super(message);
    }
}
