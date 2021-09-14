package com.microservice.booking.reservationservice.controller;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import validators.LoggedUser;
import web.AppUser;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

  @Autowired ReservationService reservationService;

  @GetMapping("/reserve")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<ReservationResponseDTO>> getAll() {
    return ResponseEntity.ok(reservationService.getAll());
  }

  @GetMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
    return ResponseEntity.ok(reservationService.getReservationById(id));
  }

  @GetMapping("/reserve/station/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<ReservationResponseDTO>> getReservationByStationId(
      @PathVariable Long id, @RequestHeader(value = "Authorization") String auth) {
    return ResponseEntity.ok(reservationService.getReservationsForComputerId(id, auth));
  }

  @PostMapping("/reserve")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> createReservation(
      @Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
      @RequestHeader(value = "Authorization") String auth,
      @LoggedUser AppUser appUser) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reservationService.createReservation(reservationRequestDTO, auth, appUser));
  }

  @PutMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> updateReservation(
      @Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
      @PathVariable Long id,
      @RequestHeader(value = "Authorization") String auth,
      @LoggedUser AppUser appUser) {
    return ResponseEntity.ok(
        reservationService.updateReservation(reservationRequestDTO, id, auth, appUser));
  }

  @DeleteMapping("/reserve/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ReservationResponseDTO> deleteReservationById(
      @PathVariable Long id, @LoggedUser AppUser appUser) {
    reservationService.deleteReservation(id, appUser);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/reserve/user")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<?> deleteReservationsForUser(@LoggedUser AppUser appUser) {
    reservationService.deleteReservationsForUser(appUser);
    return ResponseEntity.noContent().build();
  }
}
