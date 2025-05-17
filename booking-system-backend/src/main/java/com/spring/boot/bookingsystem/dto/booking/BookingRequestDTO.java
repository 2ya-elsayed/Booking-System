package com.spring.boot.bookingsystem.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.*;

//@Getter
//@Setter
@ToString
@NoArgsConstructor
public class BookingRequestDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
