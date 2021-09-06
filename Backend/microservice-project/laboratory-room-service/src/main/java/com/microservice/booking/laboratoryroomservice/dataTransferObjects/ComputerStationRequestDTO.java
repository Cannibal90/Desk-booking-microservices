package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import com.microservice.booking.laboratoryroomservice.entity.domain.NetworkType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ComputerStationRequestDTO {
    private int monitors;
    private boolean headphones;
    private boolean microphone;
    private String operatingSystem;
    private String graphicCard;
    private String cpu;
    private int ram;
    private int drive;
    @Enumerated(value = EnumType.STRING)
    private NetworkType networkType;

    private Long deskId;
}
