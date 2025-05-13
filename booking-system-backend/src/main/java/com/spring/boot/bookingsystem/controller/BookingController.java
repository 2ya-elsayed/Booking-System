package com.spring.boot.bookingsystem.controller;

import com.spring.boot.bookingsystem.dto.booking.BookingRequestDTO;
import com.spring.boot.bookingsystem.dto.booking.BookingResponseDTO;
import com.spring.boot.bookingsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Book an event
    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookEvent(@Valid @RequestBody BookingRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.bookEvent(dto.getUserId(), dto.getEventId()));
    }

    // List all bookings for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }
}

