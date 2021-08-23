package com.microservice.booking.laboratoryroomservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratoryRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int floor;
    private String roomSupervisor;

    @OneToMany
    @JoinColumn(name="LR_ID")
    private List<Desk> desks = new ArrayList<>();
}
