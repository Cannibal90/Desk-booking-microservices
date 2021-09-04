package com.microservice.booking.userservice.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassDTO {
    private String oldPassword;
    private String newPassword;
}
