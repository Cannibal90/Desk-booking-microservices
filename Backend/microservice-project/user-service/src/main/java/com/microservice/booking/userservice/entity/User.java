package com.microservice.booking.userservice.entity;

import com.microservice.booking.userservice.domain.Role;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne
    private UserDetails userDetails;

}
