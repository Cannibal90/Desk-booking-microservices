package com.microservice.booking.userservice.dataTransferObjects;

import com.microservice.booking.userservice.domain.Role;
import com.microservice.booking.userservice.validators.UniqueEmail;
import com.microservice.booking.userservice.validators.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "Username nie może być puste")
    @Size(min = 2, max = 15)
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password nie może być puste")
    private String password;

    @UniqueEmail
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Email ma zły format!")
    private String email;

    @NotBlank(message = "Name nie może być puste")
    @Size(min = 2, max = 15)
    private String name;

    @NotBlank(message = "Surname nie może być puste")
    @Size(min = 3, max = 15)
    private String surname;

    @NotNull(message = "Age nie może być pusty!")
    @Digits(integer = 2, fraction = 0)
    private int age;

    @NotBlank(message = "City nie może być puste!")
    private String city;
}
