package com.example.exceptions_demo.dto;

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
