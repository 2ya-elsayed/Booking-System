package com.spring.boot.bookingsystem.service.impl;

import com.spring.boot.bookingsystem.dto.booking.BookingResponseDTO;
import com.spring.boot.bookingsystem.exception.ConflictException;
import com.spring.boot.bookingsystem.exception.NotFoundException;
import com.spring.boot.bookingsystem.mapper.BookingMapper;
import com.spring.boot.bookingsystem.model.BookingModel;
import com.spring.boot.bookingsystem.model.EventModel;
import com.spring.boot.bookingsystem.model.UserModel;
import com.spring.boot.bookingsystem.repository.BookingRepository;
import com.spring.boot.bookingsystem.repository.EventRepository;
import com.spring.boot.bookingsystem.repository.UserRepository;
import com.spring.boot.bookingsystem.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, EventRepository eventRepository,
                              UserRepository userRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingResponseDTO bookEvent(Long userId, Long eventId) {
        if (bookingRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new ConflictException("User has already booked this event.");
        }

        UserModel user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("User not found with ID: " + userId));
        EventModel event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Event not found with ID: " + eventId));

        BookingModel booking = new BookingModel();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setBookingDate(new Date());

        return bookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
}

