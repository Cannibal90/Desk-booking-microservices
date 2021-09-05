package com.microservice.booking.reservationservice.mapper;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponseDTO toReservationResponse(Reservation reservation);

    List<ReservationResponseDTO> toReservationResponseList(List<Reservation> reservations);

    default Reservation toDomain(ReservationRequestDTO reservationRequestDTO){
        //check all important things
        Reservation reservation =  new Reservation();
        reservation.setUserId(reservationRequestDTO.getUserId());
        reservation.setStationId(reservationRequestDTO.getStationId());
        Durration durration = new Durration();
        durration.setBeginning(reservationRequestDTO.getBeginning());
        durration.setEnd(reservationRequestDTO.getEnd());
        reservation.setDurration(durration);
        return reservation;
    }

}
