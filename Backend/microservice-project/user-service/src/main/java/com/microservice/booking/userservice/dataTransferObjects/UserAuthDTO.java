package com.microservice.booking.userservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

    @NotBlank(message = "Username nie może być puste")
    @Size(min = 2, max = 15)
    private String username;

    @NotBlank(message = "Password nie może być puste")
    private String password;
}
