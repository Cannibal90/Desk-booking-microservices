package com.microservice.booking.reservationservice.entity;

import lombok.*;

import javax.persistence.*;

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

    private Long userId;
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
