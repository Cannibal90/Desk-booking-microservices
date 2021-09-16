package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryRoomRequestDTO {

    @NotNull
    private int floor;

    @NotBlank(message = "Room supervisor nie może być puste")
    @Size(min = 2, max = 30)
    private String roomSupervisor;
}
