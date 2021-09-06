package com.microservice.booking.userservice.dataTransferObjects;

import com.microservice.booking.userservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String username;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String name;
    private String surname;
    private int age;
    private String city;
}
