package com.example.crud_users.exceptions;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException() {
        super("Id not found in database, please try again");
    }

    public IdNotFoundException(String message) {
        super(message);
    }


}
