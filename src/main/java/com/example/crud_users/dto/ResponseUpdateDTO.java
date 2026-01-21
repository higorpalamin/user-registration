package com.example.crud_users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseUpdateDTO(

        UUID id,
        String firstName,
        String lastName,
        String email,
        String cpf,
        LocalDateTime updateDateTime
) {
}
