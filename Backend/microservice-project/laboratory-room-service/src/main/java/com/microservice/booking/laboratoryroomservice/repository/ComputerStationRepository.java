package com.microservice.booking.laboratoryroomservice.repository;

import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerStationRepository extends JpaRepository<ComputerStation, Long> {
}
