package com.spring.boot.bookingsystem.dto.user;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
}
