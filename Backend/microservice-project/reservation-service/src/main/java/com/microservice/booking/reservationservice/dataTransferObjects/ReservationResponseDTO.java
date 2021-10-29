package com.microservice.booking.reservationservice.dataTransferObjects;

import com.microservice.booking.reservationservice.entity.Durration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private DurrationResponseDTO durration;
    private Long StationId;
    private Long userId;

    @Override
    public String toString() {
        return "ReservationResponseDTO{" +
                "id=" + id +
                ", duration=" + durration +
                ", StationId=" + StationId +
                '}';
    }
}
