package com.spring.boot.bookingsystem.service;

import com.spring.boot.bookingsystem.dto.booking.BookingResponseDTO;

import java.util.List;

public interface BookingService {
    BookingResponseDTO bookEvent(Long userId, Long eventId);
    List<BookingResponseDTO> getBookingsByUserId(Long userId);
}

