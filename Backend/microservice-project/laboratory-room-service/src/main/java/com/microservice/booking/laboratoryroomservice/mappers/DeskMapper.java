package com.microservice.booking.laboratoryroomservice.mappers;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import com.microservice.booking.laboratoryroomservice.entity.Desk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeskMapper {

  @Named("countStations")
    default int stationsToCount(List<ComputerStation> stations) {
        return stations.size();
    }

    @Mapping(target = "stationsCount", source = "computerStations", qualifiedByName = "countStations")
    DeskResponseDTO toDeskResponse(Desk desk);

    List<DeskResponseDTO> toDeskResponseList(List<Desk> desks);

    default Desk toDomain(DeskRequestDTO deskRequestDTO){
        if(deskRequestDTO == null || deskRequestDTO.getRoomId() <= 0)
            return null;
        Desk desk = new Desk();
        desk.setDeskType(deskRequestDTO.getDeskType());
        return desk;
    }



}
