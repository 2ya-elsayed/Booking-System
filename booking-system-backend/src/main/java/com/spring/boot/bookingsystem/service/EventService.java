package com.spring.boot.bookingsystem.service;

import com.spring.boot.bookingsystem.dto.event.EventRequestDTO;
import com.spring.boot.bookingsystem.dto.event.EventResponseDTO;

import java.util.List;

public interface EventService {
    EventResponseDTO createEvent(EventRequestDTO dto, Long adminId);
    List<EventResponseDTO> getAllEvents(Long userId);
    EventResponseDTO getEventById(Long id, Long userId);
}

