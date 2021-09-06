package com.microservice.booking.reservationservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DurrationResponseDTO {
    private Long id;
    private LocalDateTime beginning;
    private LocalDateTime end;
}
