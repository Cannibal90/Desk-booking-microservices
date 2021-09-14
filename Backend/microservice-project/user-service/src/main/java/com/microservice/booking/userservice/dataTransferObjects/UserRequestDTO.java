package com.microservice.booking.userservice.dataTransferObjects;

import com.microservice.booking.userservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Username nie może być puste")
    @Size(min = 2, max = 15)
    private String username;

    @NotBlank(message = "Email nie może być puste")
    private String email;

    @NotNull
    private Role role;

    @NotNull
    @Min(value = 1, message = "DetailId nie moze byc mniejsze od 1")
    private Long detailId;

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
