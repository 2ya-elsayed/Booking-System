package com.spring.boot.bookingsystem.dto.event;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EventResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Date date;
    private String venue;
    private Double price;
    private String imageUrl;
    private boolean booked; // true if current user booked it
}
