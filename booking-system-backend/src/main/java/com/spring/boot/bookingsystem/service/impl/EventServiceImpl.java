package com.spring.boot.bookingsystem.service.impl;

import com.spring.boot.bookingsystem.dto.event.EventRequestDTO;
import com.spring.boot.bookingsystem.dto.event.EventResponseDTO;
import com.spring.boot.bookingsystem.exception.NotFoundException;
import com.spring.boot.bookingsystem.mapper.EventMapper;
import com.spring.boot.bookingsystem.model.EventModel;
import com.spring.boot.bookingsystem.model.UserModel;
import com.spring.boot.bookingsystem.repository.BookingRepository;
import com.spring.boot.bookingsystem.repository.EventRepository;
import com.spring.boot.bookingsystem.repository.UserRepository;
import com.spring.boot.bookingsystem.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository,
                            BookingRepository bookingRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventResponseDTO createEvent(EventRequestDTO dto, Long adminId) {
        // Check if admin exists
        UserModel admin = userRepository.findById(adminId).orElseThrow(() ->
                new NotFoundException("Admin not found with ID: " + adminId));

        EventModel event = eventMapper.toEntity(dto);
        event.setCreatedBy(admin);
        return eventMapper.toDTO(eventRepository.save(event));
    }

    @Override
    public List<EventResponseDTO> getAllEvents(Long userId) {
        List<EventModel> events = eventRepository.findAll();
        return events.stream().map(event -> {
            EventResponseDTO dto = eventMapper.toDTO(event);
            boolean booked = bookingRepository.existsByUserIdAndEventId(userId, event.getId());
            dto.setBooked(booked);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(Long id, Long userId) {
        // Check if the event exists
        EventModel event = eventRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Event not found with ID: " + id));

        EventResponseDTO dto = eventMapper.toDTO(event);
        dto.setBooked(bookingRepository.existsByUserIdAndEventId(userId, id));
        return dto;
    }
}
