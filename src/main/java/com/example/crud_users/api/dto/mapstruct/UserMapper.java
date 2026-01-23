package com.example.crud_users.api.dto.mapstruct;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.ResponseUpdatedDTO;
import com.example.crud_users.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "createDateTime", expression = "java(getNow())")
    UserEntity requestToEntity(RequestDTO request);

    ResponseDTO toResponseDTO(UserEntity entity);

    ResponseUpdatedDTO toResponseUpdateDTO(UserEntity entity);

    List<ResponseUpdatedDTO> toListUsers(List<UserEntity> list);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }


}
