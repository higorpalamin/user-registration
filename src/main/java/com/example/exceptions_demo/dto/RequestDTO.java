package com.example.exceptions_demo.dto;

public record RequestDTO(

        String firstName,
        String lastName,
        String email,
        String cpf
) {
}
