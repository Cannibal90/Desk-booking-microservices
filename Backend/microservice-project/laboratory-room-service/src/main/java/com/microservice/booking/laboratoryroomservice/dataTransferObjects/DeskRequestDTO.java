package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import com.microservice.booking.laboratoryroomservice.entity.domain.DeskType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DeskRequestDTO {

    @Enumerated(value= EnumType.STRING)
    @NotNull
    private DeskType deskType;

    @NotNull
    private Long roomId;
}
