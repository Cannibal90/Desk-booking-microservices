package com.microservice.booking.userservice.mapper;

import com.microservice.booking.userservice.dataTransferObjects.UserResponseDTO;
import com.microservice.booking.userservice.dataTransferObjects.UserResponseTokenDTO;
import com.microservice.booking.userservice.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-04T22:55:52+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setUsername( user.getUsername() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setRole( user.getRole() );
        userResponseDTO.setUserDetails( user.getUserDetails() );

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> toUserResponseList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }

    @Override
    public UserResponseTokenDTO toToken(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseTokenDTO userResponseTokenDTO = new UserResponseTokenDTO();

        userResponseTokenDTO.setId( user.getId() );
        userResponseTokenDTO.setUsername( user.getUsername() );
        userResponseTokenDTO.setEmail( user.getEmail() );
        userResponseTokenDTO.setRole( user.getRole() );
        userResponseTokenDTO.setUserDetails( user.getUserDetails() );

        return userResponseTokenDTO;
    }
}
