package com.microservice.booking.laboratoryroomservice.repository;

import com.microservice.booking.laboratoryroomservice.entity.LaboratoryRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LaboratoryRoomRepository extends JpaRepository<LaboratoryRoom, Long> {
}
