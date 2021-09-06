package com.microservice.booking.reservationservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    private Long userId;
    private Long StationId;

    private LocalDateTime beginning;
    private LocalDateTime end;
}
