package com.microservice.booking.userservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassDTO {

    @NotBlank(message = "Password nie może być puste")
    private String oldPassword;

    @NotBlank(message = "Password nie może być puste")
    private String newPassword;
}
