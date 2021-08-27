package com.microservice.booking.laboratoryroomservice.repository;

import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ComputerStationRepository extends JpaRepository<ComputerStation, Long> {
}
