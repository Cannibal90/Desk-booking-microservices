package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import com.microservice.booking.laboratoryroomservice.entity.domain.DeskType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DeskResponseDTO {
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DeskType deskType;
    private Long roomId;
    private int stationsCount;
}
