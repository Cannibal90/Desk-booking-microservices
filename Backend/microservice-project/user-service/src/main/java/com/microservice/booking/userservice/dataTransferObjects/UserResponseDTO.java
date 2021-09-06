package com.microservice.booking.userservice.dataTransferObjects;

import com.microservice.booking.userservice.domain.Role;
import com.microservice.booking.userservice.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private UserDetails userDetails;

}
