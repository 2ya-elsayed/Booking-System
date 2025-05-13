package com.spring.boot.bookingsystem.service.impl;

import com.spring.boot.bookingsystem.dto.user.UserRequestDTO;
import com.spring.boot.bookingsystem.dto.user.UserResponseDTO;
import com.spring.boot.bookingsystem.exception.ConflictException;
import com.spring.boot.bookingsystem.exception.NotFoundException;
import com.spring.boot.bookingsystem.mapper.UserMapper;
import com.spring.boot.bookingsystem.model.UserModel;
import com.spring.boot.bookingsystem.repository.UserRepository;
import com.spring.boot.bookingsystem.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
        // Check if user already exists (you could also throw ConflictException if needed)
        Optional<UserModel> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new ConflictException("User already exists with email: " + dto.getEmail());
        }

        UserModel user = userMapper.toEntity(dto);
        user.setRole("user");
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        // Check if the user exists
        UserModel user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found with ID: " + id));
        return userMapper.toDTO(user);
    }
}
