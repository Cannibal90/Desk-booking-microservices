package com.microservice.booking.laboratoryroomservice.entity;

import javax.persistence.*;
import com.microservice.booking.laboratoryroomservice.entity.domain.NetworkType;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ComputerStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int monitors;
    private boolean headphones;
    private boolean microphone;
    private String operatingSystem;
    private String graphicCard;
    private String cpu;
    private int ram;
    private int drive;
    @Enumerated(value = EnumType.STRING)
    private NetworkType networkType;
}
