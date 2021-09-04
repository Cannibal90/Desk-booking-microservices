package com.microservice.booking.userservice.service;

import com.microservice.booking.userservice.dataTransferObjects.*;
import com.microservice.booking.userservice.entity.User;
import com.microservice.booking.userservice.mapper.UserMapper;
import com.microservice.booking.userservice.repository.UserRepository;
import exception.ApiNoFoundResourceException;
import exception.ApiWrongParameterException;
import exception.ExceptionConst;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAll(){
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userDTO = new ArrayList<>();
        userDTO.addAll(userMapper.toUserResponseList(users));
        return userDTO;
    }

    public UserResponseDTO getUserById(Long id){
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var user = userRepository.findById(id);
        if(user.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

        return userMapper.toUserResponse(user.get());
    }

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        if(userCreateDTO == null)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var user = userMapper.toCreateDomain(userCreateDTO);
        String password = passwordEncoder.encode(userCreateDTO.getPassword());
        user.setPassword(password);
        var saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }

    public UserResponseTokenDTO authorizeUser(UserAuthDTO userAuthDTO) {
        Optional<User> optionalUser = userRepository.
                findAll()
                .stream()
                .filter(u-> u.getUsername().equals(userAuthDTO.getUsername()) && passwordEncoder.matches(userAuthDTO.getPassword(), u.getPassword()))
                .findFirst();
        if(optionalUser.isEmpty())
            throw new ApiWrongParameterException(ExceptionConst.WRONG_CREDENTIALS);

        Key key = Keys.hmacShaKeyFor("BvPHGM8C0ia4uOuxxqPD5DTbWC9F9TWvPStp3pb7ARo0oK2mJ3pd3YG4lxA9i8bj6OTbadwezxgeEByY".getBytes());
        User user = optionalUser.get();
        String token = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("id",""+user.getId())
                .claim("role", user.getRole().toString())
                .signWith(key).compact();
        var response = userMapper.toToken(user);
        response.setToken(token);
        return response;
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id){
        if(userRequestDTO == null || id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var user = userRepository.findById(id);
        if(user.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

        var userToUpdate = userMapper.toDomain(userRequestDTO);
        userToUpdate.setId(user.get().getId());
        userToUpdate.setPassword(user.get().getPassword());
        userToUpdate.getUserDetails().setId(user.get().getUserDetails().getId());

        var updatedUser = userRepository.save(userToUpdate);
        return userMapper.toUserResponse(updatedUser);
    }

    public UserResponseDTO changePassword(UserPassDTO userPassDTO, Long id) {
        if(userPassDTO == null || id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var user = userRepository.findById(id);
        if(user.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

        var userToUpdate = user.get();
        if(userToUpdate.getPassword().equals(passwordEncoder.encode(userPassDTO.getOldPassword())))
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);
        userToUpdate.setPassword(passwordEncoder.encode(userPassDTO.getNewPassword()));

        var updatedUser = userRepository.save(userToUpdate);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(Long id){
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        if(userRepository.findById(id).isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

        //TODO usunac wszystkie rezerwacje usera
        userRepository.deleteById(id);
    }
}
