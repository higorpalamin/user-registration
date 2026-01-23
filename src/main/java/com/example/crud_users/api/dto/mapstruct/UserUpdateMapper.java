package com.example.crud_users.api.dto.mapstruct;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdateMapper {

    @Mapping(target = "updateDateTime", expression = "java(getNow())")
    UserEntity updateUser(RequestDTO dto, @MappingTarget UserEntity entity);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
