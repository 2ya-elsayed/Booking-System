package com.spring.boot.bookingsystem.dto.booking;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private Date bookingDate;
    private Long userId;
    private Long eventId;
}
