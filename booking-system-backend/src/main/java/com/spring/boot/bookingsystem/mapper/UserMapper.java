package com.spring.boot.bookingsystem.mapper;

import com.spring.boot.bookingsystem.dto.user.UserRequestDTO;
import com.spring.boot.bookingsystem.dto.user.UserResponseDTO;
import com.spring.boot.bookingsystem.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDTO(UserModel user);

    UserModel toEntity(UserRequestDTO dto);
}
