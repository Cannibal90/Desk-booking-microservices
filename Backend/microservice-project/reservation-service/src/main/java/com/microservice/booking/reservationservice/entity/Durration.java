package com.microservice.booking.reservationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long stationId;
    private LocalDateTime beginning;
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
