package com.microservice.booking.reservationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Durration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Long stationId;

    @NotNull
    private LocalDateTime beginning;

    @NotNull
    private LocalDateTime end;

    @Override
    public String toString() {
        return "Durration{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", beginning=" + beginning +
                ", end=" + end +
                '}';
    }
}
