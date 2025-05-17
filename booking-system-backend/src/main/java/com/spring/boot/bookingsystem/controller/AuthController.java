package com.spring.boot.bookingsystem.controller;

import com.spring.boot.bookingsystem.dto.user.AuthenticationRequest;
import com.spring.boot.bookingsystem.dto.user.RegisterRequest;
import com.spring.boot.bookingsystem.dto.user.UserRequestDTO;
import com.spring.boot.bookingsystem.model.auth.AuthenticationResponse;
import com.spring.boot.bookingsystem.service.AuthentictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthentictionService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(
            @RequestBody AuthenticationRequest request
            ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
