package com.example.crud_users.domain.service;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.ResponseUpdatedDTO;
import com.example.crud_users.api.dto.mapstruct.UserMapper;
import com.example.crud_users.api.dto.mapstruct.UserUpdateMapper;
import com.example.crud_users.domain.entity.UserEntity;
import com.example.crud_users.exceptions.CpfAlreadyExistsException;
import com.example.crud_users.exceptions.EmailAlreadyExistsException;
import com.example.crud_users.exceptions.IdNotFoundException;
import com.example.crud_users.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUpdateMapper updateMapper;

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

        return mapper.toResponseDTO(UUIDtoResponse);
    }

    public ResponseUpdatedDTO updateUserById(UUID uuid, RequestDTO request) {
        UserEntity entity = userRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException()
                );

        UserEntity toEntity = updateMapper.updateUser(request, entity);
        return mapper.toResponseUpdateDTO(userRepository.saveAndFlush(toEntity));
    }

    public List<ResponseUpdatedDTO> getAll(){
        return mapper.toListUsers(userRepository.findAll());
    }

    public void deleteUserById(UUID uuid) {

        if (!userRepository.existsById(uuid)) {
            throw new IdNotFoundException();
        }
        userRepository.deleteById(uuid);
    }

}

