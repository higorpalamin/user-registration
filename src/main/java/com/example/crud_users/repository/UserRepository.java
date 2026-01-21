package com.example.crud_users.repository;

import com.example.crud_users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsById(UUID uuid);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
