package com.example.exceptions_demo.exceptions;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException() {
        super("Id not found in database, please try again");
    }

    public IdNotFoundException(String message) {
        super(message);
    }


}
