package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryRoomResponseDTO {
    private Long id;
    private int floor;
    private String roomSupervisor;
    private int deskCount;

    @Override
    public String toString() {
        return "LaboratoryRoomResponseDTO{" +
                "id=" + id +
                ", floor=" + floor +
                ", roomSupervisor='" + roomSupervisor + '\'' +
                ", deskCount=" + deskCount +
                '}';
    }

}
