package com.microservice.booking.userservice.controller;


import com.microservice.booking.userservice.dataTransferObjects.*;
import com.microservice.booking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDTO));
    }

    @PostMapping("user/login")
    public ResponseEntity<UserResponseTokenDTO> authorizeUser(@RequestBody UserAuthDTO userAuthDTO) {
        return ResponseEntity.ok(userService.authorizeUser(userAuthDTO));
    }
    //TODO dodac sprawdzenie czy to ten sam jak nie jest adminem
    @PutMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(userRequestDTO, id));
    }

    //TODO dodac sprawdzenie czy to ten sam jak nie jest adminem
    @PutMapping("/user/password/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserResponseDTO> changePassword(@RequestBody UserPassDTO userPassDTO, @PathVariable Long id){
        return ResponseEntity.ok(userService.changePassword(userPassDTO, id));
    }

    //TODO dodac sprawdzenie czy to ten sam jak nie jest adminem
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/check/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Boolean> checkUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.checkUser(id));
    }
}
