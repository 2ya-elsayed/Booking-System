package com.spring.boot.bookingsystem.mapper;

import com.spring.boot.bookingsystem.dto.event.EventRequestDTO;
import com.spring.boot.bookingsystem.dto.event.EventResponseDTO;
import com.spring.boot.bookingsystem.model.EventModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponseDTO toDTO(EventModel event);

    EventModel toEntity(EventRequestDTO dto);
}
