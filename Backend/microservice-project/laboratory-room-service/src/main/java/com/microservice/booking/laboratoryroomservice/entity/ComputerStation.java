package com.microservice.booking.laboratoryroomservice.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Min(1)
    private int monitors;

    @NotNull
    private boolean headphones;

    @NotNull
    private boolean microphone;

    @NotBlank
    @Size(min = 3, max = 25)
    private String operatingSystem;

    @NotBlank
    @Size(min = 3, max = 25)
    private String graphicCard;

    @NotBlank
    @Size(min = 3, max = 25)
    private String cpu;

    @Min(2)
    @NotNull
    private int ram;

    @Min(256)
    @NotNull
    private int drive;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private NetworkType networkType;
}
