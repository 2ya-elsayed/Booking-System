package com.spring.boot.bookingsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // "user" or "admin"

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookingModel> bookings = new ArrayList<>();
}