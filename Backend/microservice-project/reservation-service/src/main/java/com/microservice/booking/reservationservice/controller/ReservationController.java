package com.microservice.booking.reservationservice.controller;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("reservation")
public class ReservationController {

  @Autowired ReservationService reservationService;

  @ApiOperation(
      value = "Find all reservations",
      response = ReservationResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/reserve")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<ReservationResponseDTO>> getAll() {
    return ResponseEntity.ok(reservationService.getAll());
  }

  @ApiOperation(value = "Find reservation by Id", response = ReservationResponseDTO.class)
  @GetMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> getReservationById(
      @ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(reservationService.getReservationById(id));
  }

  @ApiOperation(
      value = "Find reservations for station with provided Id",
      response = ReservationResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/reserve/station/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<ReservationResponseDTO>> getReservationByStationId(
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @ApiParam(value = "Authorization token ") @RequestHeader(value = "Authorization") String auth) {
    return ResponseEntity.ok(reservationService.getReservationsForComputerId(id, auth));
  }

  @ApiOperation(value = "Create new reservation", response = ReservationResponseDTO.class)
  @PostMapping("/reserve")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> createReservation(
      @Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
      @ApiParam(value = "Authorization token ") @RequestHeader(value = "Authorization") String auth,
      @ApiIgnore  @LoggedUser AppUser appUser) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reservationService.createReservation(reservationRequestDTO, auth, appUser));
  }

  @ApiOperation(
      value = "Update reservation with provided Id",
      response = ReservationResponseDTO.class)
  @PutMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> updateReservation(
      @Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @ApiParam(value = "Authorization token ") @RequestHeader(value = "Authorization") String auth,
      @ApiIgnore @LoggedUser AppUser appUser) {
    return ResponseEntity.ok(
        reservationService.updateReservation(reservationRequestDTO, id, auth, appUser));
  }

  @ApiOperation(
      value = "Delete reservation with provided Id",
      response = ReservationResponseDTO.class)
  @DeleteMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> deleteReservationById(
      @ApiParam(defaultValue = "1") @PathVariable Long id,@ApiIgnore  @LoggedUser AppUser appUser) {
    reservationService.deleteReservation(id, appUser);
    return ResponseEntity.noContent().build();
  }

  @ApiIgnore
  @DeleteMapping("/reserve/user")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<?> deleteReservationsForUser(@ApiIgnore @LoggedUser AppUser appUser) {
    reservationService.deleteReservationsForUser(appUser);
    return ResponseEntity.noContent().build();
  }
}
