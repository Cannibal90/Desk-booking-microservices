package com.microservice.booking.userservice.entity;

import com.microservice.booking.userservice.domain.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private UserDetails userDetails;

}
