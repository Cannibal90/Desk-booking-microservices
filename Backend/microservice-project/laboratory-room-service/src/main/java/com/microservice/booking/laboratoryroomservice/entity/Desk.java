package com.microservice.booking.laboratoryroomservice.entity;


import com.microservice.booking.laboratoryroomservice.entity.domain.DeskType;
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
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DeskType deskType;
    //ILE PC
    @OneToMany
    @JoinColumn(name="desk_ID")
    private List<ComputerStation> computerStations = new ArrayList<>();

}
