package com.example.exceptions_demo.service;

import com.example.exceptions_demo.dto.RequestDTO;
import com.example.exceptions_demo.dto.ResponseDTO;
import com.example.exceptions_demo.dto.ResponseUpdateDTO;
import com.example.exceptions_demo.entity.UserEntity;
import com.example.exceptions_demo.exceptions.CpfAlreadyExistsException;
import com.example.exceptions_demo.exceptions.EmailAlreadyExistsException;
import com.example.exceptions_demo.exceptions.IdNotFoundException;
import com.example.exceptions_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseDTO saveUser(RequestDTO request){

        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException();
        }
        if(userRepository.existsByCpf(request.cpf())){
            throw new CpfAlreadyExistsException();
        }

        UserEntity requestToEntity = UserEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .cpf(request.cpf())
                .createDateTime(LocalDateTime.now())
                .build();

        UserEntity entityToResponse = userRepository.saveAndFlush(requestToEntity);

        return toResponseDTO(entityToResponse);
    }

    public ResponseDTO findById(UUID uuid){

        UserEntity UUIDtoResponse = userRepository.findById(uuid)
                .orElseThrow(()-> new IdNotFoundException());

        return toResponseDTO(UUIDtoResponse);
    }

    public void deleteUserById(UUID uuid){

        if(!userRepository.existsById(uuid)) {
            throw new IdNotFoundException();
        }
        userRepository.deleteById(uuid);
    }

    public ResponseUpdateDTO updateUserById(UUID uuid, RequestDTO request){
        UserEntity var = userRepository.findById(uuid)
                .orElseThrow(()-> new IdNotFoundException()
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

    public ResponseDTO toResponseDTO(UserEntity toResponse){
        return new ResponseDTO(
                toResponse.getId(),
                toResponse.getFirstName(),
                toResponse.getLastName(),
                toResponse.getEmail(),
                toResponse.getCpf(),
                toResponse.getCreateDateTime()
        );
    }

    public ResponseUpdateDTO toResponseUpdateDTO(UserEntity toResponse){
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

