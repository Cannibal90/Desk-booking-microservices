package com.microservice.booking.userservice.controller;


import com.microservice.booking.userservice.dataTransferObjects.*;
import com.microservice.booking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDTO));
    }

    @PostMapping("user/login")
    public ResponseEntity<UserResponseTokenDTO> authorizeUser(@RequestBody UserAuthDTO userAuthDTO) {
        return ResponseEntity.ok(userService.authorizeUser(userAuthDTO));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(userRequestDTO, id));
    }

    @PutMapping("/user/password/{id}")
    public ResponseEntity<UserResponseDTO> changePassword(@RequestBody UserPassDTO userPassDTO, @PathVariable Long id){
        return ResponseEntity.ok(userService.changePassword(userPassDTO, id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
