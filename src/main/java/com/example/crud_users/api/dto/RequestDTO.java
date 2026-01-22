package com.example.crud_users.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestDTO(

        @NotBlank(message = "Name is required.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        String lastName,

        @NotBlank(message = "E-mail is required.")
        String email,

        @NotBlank(message = "CPF is required.")
        String cpf
) {
}
