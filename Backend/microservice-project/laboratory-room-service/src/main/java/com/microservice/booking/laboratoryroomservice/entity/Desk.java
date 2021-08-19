package com.microservice.booking.laboratoryroomservice.entity;


import com.microservice.booking.laboratoryroomservice.entity.domain.DeskType;

import javax.persistence.*;

@Entity
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DeskType deskType;
    //ILE PC

}
