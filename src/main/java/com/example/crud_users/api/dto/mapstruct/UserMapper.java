package com.example.crud_users.api.dto.mapstruct;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity requestToEntity(RequestDTO request);

    ResponseDTO toResponseDTO(UserEntity entity);
}
