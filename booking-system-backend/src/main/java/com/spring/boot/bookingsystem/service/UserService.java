package com.spring.boot.bookingsystem.service;

import com.spring.boot.bookingsystem.dto.user.UserRequestDTO;
import com.spring.boot.bookingsystem.dto.user.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id);
}
