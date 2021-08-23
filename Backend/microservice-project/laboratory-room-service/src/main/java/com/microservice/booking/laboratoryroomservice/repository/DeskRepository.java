package com.microservice.booking.laboratoryroomservice.repository;

import com.microservice.booking.laboratoryroomservice.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk, Long> {
}
