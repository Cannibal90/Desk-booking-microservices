package com.microservice.booking.reservationservice.controller;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reserve")
    public ResponseEntity<List<ReservationResponseDTO>> getAll(){
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/reserve/station/{id}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByStationId(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservationsForComputerId(id));
    }

    @PostMapping("/reserve")
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationRequestDTO));
    }

    @PutMapping("/reserve/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@RequestBody ReservationRequestDTO reservationRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok(reservationService.updateReservation(reservationRequestDTO,id));
    }

    @DeleteMapping("/reserve/{id}")
    public ResponseEntity<ReservationResponseDTO> deleteReservationById(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
