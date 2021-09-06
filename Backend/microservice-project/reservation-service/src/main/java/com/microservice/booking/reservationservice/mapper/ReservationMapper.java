package com.microservice.booking.reservationservice.mapper;

import com.microservice.booking.reservationservice.dataTransferObjects.DurrationResponseDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Named("mapDurration")
    default DurrationResponseDTO toDurrationResponse(Durration durration){
        DurrationResponseDTO durrationResponseDTO = new DurrationResponseDTO();
        durrationResponseDTO.setId(durration.getId());
        durrationResponseDTO.setBeginning(durration.getBeginning());
        durrationResponseDTO.setEnd(durration.getEnd());
        return durrationResponseDTO;
    }

    @Mapping(target = "durration", source = "durration", qualifiedByName = "mapDurration")
    ReservationResponseDTO toReservationResponse(Reservation reservation);

    List<ReservationResponseDTO> toReservationResponseList(List<Reservation> reservations);

    default Reservation toDomain(ReservationRequestDTO reservationRequestDTO){
        //check all important things
        Reservation reservation =  new Reservation();
        reservation.setUserId(reservationRequestDTO.getUserId());
        reservation.setStationId(reservationRequestDTO.getStationId());
        Durration durration = new Durration();
        durration.setStationId(reservationRequestDTO.getStationId());
        durration.setBeginning(reservationRequestDTO.getBeginning());
        durration.setEnd(reservationRequestDTO.getEnd());
        reservation.setDurration(durration);
        return reservation;
    }

}
