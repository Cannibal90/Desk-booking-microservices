package com.microservice.booking.reservationservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long StationId;

    @NotNull
    private LocalDateTime beginning;

    @NotNull
    private LocalDateTime end;
}
