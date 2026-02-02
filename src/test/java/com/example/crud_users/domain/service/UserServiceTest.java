package com.example.crud_users.domain.service;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.mapstruct.UserMapper;
import com.example.crud_users.api.dto.mapstruct.UserUpdateMapper;
import com.example.crud_users.domain.entity.UserEntity;
import com.example.crud_users.domain.repository.UserRepository;
import com.example.crud_users.exceptions.CpfAlreadyExistsException;
import com.example.crud_users.exceptions.EmailAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserUpdateMapper updateMapper;

    @Test
    @DisplayName("Should Create a User with success")
    void shouldCreateAUserWithSuccess() {

        //arrange
        UserEntity entity = new UserEntity(
                UUID.randomUUID(),
                "name",
                "lastName",
                "email@email.com",
                "12345678900",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        RequestDTO request = new RequestDTO(
                "name",
                "lastName",
                "email@email.com",
                "12345678900"
        );
        ResponseDTO responseDTO = new ResponseDTO(
                UUID.randomUUID(),
                "name",
                "lastName",
                "email@email.com",
                "12345678900",
                LocalDateTime.now()
        );

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByCpf(request.cpf())).thenReturn(false);
        when(mapper.requestToEntity(request)).thenReturn(entity);
        when(userRepository.saveAndFlush(entity)).thenReturn(entity);
        when(mapper.toResponseDTO(entity)).thenReturn(responseDTO);

        //act
        ResponseDTO response = userService.saveUser(request);

        //assert
        assertNotNull(response);
        assertEquals(responseDTO, response);

        //verify
        verify(userRepository).existsByEmail(request.email());
        verify(userRepository).existsByCpf(request.cpf());
        verify(userRepository).saveAndFlush(entity);
        verify(mapper).requestToEntity(request);
        verify(mapper).toResponseDTO(entity);
    }

    @Test
    @DisplayName("Should Throw Exception when email already exists")
    void shouldThrowExceptionWhenEmailAlreadyExists(){
        RequestDTO request = new RequestDTO(
                "name",
                "lastName",
                "email@email.com",
                "12345678900"
        );

        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, ()->
                userService.saveUser(request));

        verify(userRepository).existsByEmail(request.email());
        verify(userRepository, never()).existsByCpf(any());
        verify(userRepository, never()).saveAndFlush(any());
    }

    @Test
    @DisplayName("Should Throw Exception when cpf already exists")
    void shouldThrowExceptionWhenCPFAlreadyExists(){
        RequestDTO request = new RequestDTO(
                "name",
                "lastName",
                "email@email.com",
                "12345678900"
        );

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByCpf(request.cpf())).thenReturn(true);

        assertThrows(CpfAlreadyExistsException.class, ()->
                userService.saveUser(request));

        verify(userRepository).existsByEmail(request.email());
        verify(userRepository).existsByCpf(request.cpf());
        verify(userRepository, never()).saveAndFlush(any());
    }
}