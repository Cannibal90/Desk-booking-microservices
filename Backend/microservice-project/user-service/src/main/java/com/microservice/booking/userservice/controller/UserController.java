package com.microservice.booking.userservice.controller;


import com.microservice.booking.userservice.configuration.SwaggerConfiguration;
import com.microservice.booking.userservice.dataTransferObjects.*;
import com.microservice.booking.userservice.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import validators.LoggedUser;
import web.AppUser;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Api(tags = {SwaggerConfiguration.USER_TAG})
public class UserController {

  @Autowired UserService userService;

  @ApiOperation(
      value = "Find all users",
      response = UserResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/user")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<UserResponseDTO>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @ApiOperation(value = "Find user by Id", response = UserResponseDTO.class)
  @GetMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<UserResponseDTO> getUserById(
      @ApiParam(defaultValue = "1") @PathVariable Long id, @ApiIgnore @LoggedUser AppUser appUser) {
    return ResponseEntity.ok(userService.getUserById(id, appUser));
  }

  @ApiOperation(value = "Create new user", response = UserResponseDTO.class)
  @PostMapping("/user/register")
  public ResponseEntity<UserResponseDTO> createUser(
      @RequestBody @Valid UserCreateDTO userCreateDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDTO));
  }

  @ApiOperation(value = "Get token for exiting user", response = UserResponseTokenDTO.class)
  @PostMapping("user/login")
  public ResponseEntity<UserResponseTokenDTO> authorizeUser(
      @RequestBody @Valid UserAuthDTO userAuthDTO) {
    return ResponseEntity.ok(userService.authorizeUser(userAuthDTO));
  }

  @ApiOperation(value = "Update user with provided Id", response = UserResponseDTO.class)
  @PutMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<UserResponseDTO> updateUser(
      @Valid @RequestBody UserRequestDTO userRequestDTO,
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @ApiIgnore @LoggedUser AppUser appUser) {
    return ResponseEntity.ok(userService.updateUser(userRequestDTO, id, appUser));
  }

  @ApiOperation(value = "Change user's password with provided Id", response = UserResponseDTO.class)
  @PutMapping("/user/password/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<UserResponseDTO> changePassword(
      @Valid @RequestBody UserPassDTO userPassDTO,
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @ApiIgnore @LoggedUser AppUser appUser) {
    return ResponseEntity.ok(userService.changePassword(userPassDTO, id, appUser));
  }

  @ApiOperation(value = "Delete user with provided Id")
  @DeleteMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<?> deleteUserById(
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @ApiParam(value = "Authorization token ") @RequestHeader(value = "Authorization") String auth,
      @ApiIgnore @LoggedUser AppUser appUser) {
    userService.deleteUser(id, auth, appUser);
    return ResponseEntity.noContent().build();
  }

  @ApiIgnore
  @GetMapping("/user/check/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<Boolean> checkUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.checkUser(id));
  }
}
