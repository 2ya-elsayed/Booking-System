package com.spring.boot.bookingsystem.controller;

import com.spring.boot.bookingsystem.dto.event.EventRequestDTO;
import com.spring.boot.bookingsystem.dto.event.EventResponseDTO;
import com.spring.boot.bookingsystem.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Create a new event (admin only)
    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO dto,
                                                        @RequestParam Long adminId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto, adminId));
    }

    // Get all events (with booking info)
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents(@RequestParam Long userId) {
        return ResponseEntity.ok(eventService.getAllEvents(userId));
    }

    // Get a single event's details
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id,
                                                         @RequestParam Long userId) {
        return ResponseEntity.ok(eventService.getEventById(id, userId));
    }
}

