package com.spring.boot.bookingsystem.mapper;

import com.spring.boot.bookingsystem.dto.booking.BookingRequestDTO;
import com.spring.boot.bookingsystem.dto.booking.BookingResponseDTO;
import com.spring.boot.bookingsystem.model.BookingModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    BookingResponseDTO toDTO(BookingModel booking);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    BookingModel toEntity(BookingRequestDTO dto);
}