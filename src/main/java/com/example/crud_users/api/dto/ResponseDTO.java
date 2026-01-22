package com.example.crud_users.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseDTO(

        UUID id,
        String firstName,
        String lastName,
        String email,
        String cpf,
        LocalDateTime createDateTime
) {
}
