package com.microservice.booking.laboratoryroomservice.mappers;


import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.Desk;
import com.microservice.booking.laboratoryroomservice.entity.LaboratoryRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LaboratoryRoomMapper {

    @Named("countDesks")
    default int desksToCount(List<Desk> desks){
        return desks.size();
    }

    @Mapping(target = "deskCount", source = "desks", qualifiedByName = "countDesks")
    LaboratoryRoomResponseDTO toLaboratoryRoomResponse(LaboratoryRoom laboratoryRoom);

    List<LaboratoryRoomResponseDTO> toLaboratoryRoomResponseList(List<LaboratoryRoom> laboratoryRooms);


}
