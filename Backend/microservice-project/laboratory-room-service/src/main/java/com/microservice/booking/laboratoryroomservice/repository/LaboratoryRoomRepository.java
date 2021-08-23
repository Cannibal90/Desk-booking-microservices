package com.microservice.booking.laboratoryroomservice.repository;

import com.microservice.booking.laboratoryroomservice.entity.LaboratoryRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRoomRepository extends JpaRepository<LaboratoryRoom, Long> {
}
