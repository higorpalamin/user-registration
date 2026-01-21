package com.example.crud_users.dto;

public record RequestDTO(

        String firstName,
        String lastName,
        String email,
        String cpf
) {
}
