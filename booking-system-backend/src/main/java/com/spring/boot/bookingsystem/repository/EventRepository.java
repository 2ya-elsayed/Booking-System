package com.spring.boot.bookingsystem.repository;

import com.spring.boot.bookingsystem.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventModel, Long> {}

