package com.example.crud_users.domain.service;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.ResponseUpdateDTO;
import com.example.crud_users.api.dto.mapstruct.UserMapper;
import com.example.crud_users.domain.entity.UserEntity;
import com.example.crud_users.exceptions.CpfAlreadyExistsException;
import com.example.crud_users.exceptions.EmailAlreadyExistsException;
import com.example.crud_users.exceptions.IdNotFoundException;
import com.example.crud_users.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public ResponseDTO saveUser(RequestDTO request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException();
        }
        if (userRepository.existsByCpf(request.cpf())) {
            throw new CpfAlreadyExistsException();
        }

        UserEntity toEntity = mapper.requestToEntity(request);
        return mapper.toResponseDTO(userRepository.saveAndFlush(toEntity));

    }

    public ResponseDTO findById(UUID uuid) {

        UserEntity UUIDtoResponse = userRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException());

        return toResponseDTO(UUIDtoResponse);
    }

    public void deleteUserById(UUID uuid) {

        if (!userRepository.existsById(uuid)) {
            throw new IdNotFoundException();
        }
        userRepository.deleteById(uuid);
    }

    public ResponseUpdateDTO updateUserById(UUID uuid, RequestDTO request) {
        UserEntity var = userRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException()
                );

        UserEntity toUpdate = UserEntity.builder()
                .id(var.getId())
                .firstName(request.firstName() != null ? request.firstName() : var.getFirstName())
                .lastName(request.lastName() != null ? request.lastName() : var.getLastName())
                .email(var.getEmail())
                .cpf(request.cpf() != null ? request.cpf() : var.getCpf())
                .updateDateTime(LocalDateTime.now())
                .build();

        UserEntity updatedUser = userRepository.saveAndFlush(toUpdate);
        return toResponseUpdateDTO(updatedUser);
    }

    public ResponseDTO toResponseDTO(UserEntity toResponse) {
        return new ResponseDTO(
                toResponse.getId(),
                toResponse.getFirstName(),
                toResponse.getLastName(),
                toResponse.getEmail(),
                toResponse.getCpf(),
                toResponse.getCreateDateTime()
        );
    }

    public ResponseUpdateDTO toResponseUpdateDTO(UserEntity toResponse) {
        return new ResponseUpdateDTO(
                toResponse.getId(),
                toResponse.getFirstName(),
                toResponse.getLastName(),
                toResponse.getEmail(),
                toResponse.getCpf(),
                toResponse.getUpdateDateTime()
        );
    }

}

