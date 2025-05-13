package com.spring.boot.bookingsystem.repository;

import com.spring.boot.bookingsystem.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingModel, Long> {
    List<BookingModel> findByUserId(Long userId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}
