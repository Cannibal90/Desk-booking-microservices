package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryRoomRequestDTO {
    private int floor;
    private String roomSupervisor;
}
