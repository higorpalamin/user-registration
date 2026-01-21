package com.example.exceptions_demo.repository;

import com.example.exceptions_demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsById(UUID uuid);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
