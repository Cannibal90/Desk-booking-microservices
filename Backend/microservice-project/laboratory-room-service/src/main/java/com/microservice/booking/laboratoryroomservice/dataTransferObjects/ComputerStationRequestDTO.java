package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import com.microservice.booking.laboratoryroomservice.entity.domain.NetworkType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ComputerStationRequestDTO {
    @Min(1)
    @NotNull
    private int monitors;

    @NotNull
    private boolean headphones;

    @NotNull
    private boolean microphone;

    @NotBlank(message = "OperatingSystem nie może być puste")
    @Size(min = 2, max = 25)
    private String operatingSystem;

    @NotBlank(message = "GraphicCard nie może być puste")
    @Size(min = 2, max = 25)
    private String graphicCard;

    @NotBlank(message = "Cpu nie może być puste")
    @Size(min = 2, max = 25)
    private String cpu;

    @Min(2)
    @NotNull
    private int ram;

    @Min(256)
    @NotNull
    private int drive;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private NetworkType networkType;

    @NotNull
    private Long deskId;
}
