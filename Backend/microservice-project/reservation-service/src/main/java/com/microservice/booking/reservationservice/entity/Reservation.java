package com.microservice.booking.reservationservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Durration durration;

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long stationId;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", durration=" + durration +
                ", userId=" + userId +
                ", stationId=" + stationId +
                '}';
    }
}
