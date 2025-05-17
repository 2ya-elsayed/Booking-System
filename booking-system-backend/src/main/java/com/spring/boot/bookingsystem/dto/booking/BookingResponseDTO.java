package com.spring.boot.bookingsystem.dto.booking;

import java.util.Date;
import lombok.*;

//@Getter
//@Setter
@ToString
@NoArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private Date bookingDate;
    private Long userId;
    private Long eventId;

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
