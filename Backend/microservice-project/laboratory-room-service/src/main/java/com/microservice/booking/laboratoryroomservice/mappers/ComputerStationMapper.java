package com.microservice.booking.laboratoryroomservice.mappers;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputerStationMapper {

    ComputerStationResponseDTO toComputerStationResponse(ComputerStation computerStation);

    List<ComputerStationResponseDTO> toComputerStationResponseList(List<ComputerStation> computerStations);

    ComputerStation toDomain(ComputerStationRequestDTO computerStationRequestDTO);
}
