package com.spring.boot.bookingsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "events")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private String category;

    private Date eventDate;

    private String venue;

    private Double price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserModel createdBy; // Admin who created the event

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<BookingModel> bookings = new ArrayList<>();
}
