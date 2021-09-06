package com.microservice.booking.reservationservice.repository;

import com.microservice.booking.reservationservice.entity.Durration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DurrationRepository extends JpaRepository<Durration, Long> {
    List<Durration> findAllByStationId(Long id);
}
