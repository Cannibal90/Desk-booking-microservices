package com.microservice.booking.reservationservice.repository;

import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByStationId(Long id);
    List<Reservation> findAllByUserId(Long id);
}
