package com.microservice.booking.userservice.mapper;

import com.microservice.booking.userservice.dataTransferObjects.UserCreateDTO;
import com.microservice.booking.userservice.dataTransferObjects.UserRequestDTO;
import com.microservice.booking.userservice.dataTransferObjects.UserResponseDTO;
import com.microservice.booking.userservice.dataTransferObjects.UserResponseTokenDTO;
import com.microservice.booking.userservice.domain.Role;
import com.microservice.booking.userservice.entity.User;
import com.microservice.booking.userservice.entity.UserDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponse(User user);

    List<UserResponseDTO> toUserResponseList(List<User> users);

    UserResponseTokenDTO toToken(User user);

     default User toCreateDomain(UserCreateDTO userCreateDTO) {
        if(userCreateDTO == null)
            return null;
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setRole(Role.ROLE_USER);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userCreateDTO.getName());
        userDetails.setSurname(userCreateDTO.getSurname());
        userDetails.setAge(userCreateDTO.getAge());
        userDetails.setCity(userCreateDTO.getCity());
        user.setUserDetails(userDetails);
        return user;
    }

    default User toDomain(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userRequestDTO.getName());
        userDetails.setSurname(userRequestDTO.getSurname());
        userDetails.setAge(userRequestDTO.getAge());
        userDetails.setCity(userRequestDTO.getCity());
        user.setUserDetails(userDetails);
        return user;
    }
}
