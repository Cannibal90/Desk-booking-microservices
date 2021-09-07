package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.ComputerStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class ComputerStationController {
    @Autowired
    private ComputerStationService computerStationService;

    @GetMapping("/computer")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<ComputerStationResponseDTO>> getAll(){
        return ResponseEntity.ok(computerStationService.getAll());
    }

    @GetMapping("/computer/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ComputerStationResponseDTO> getComputerStationById(@PathVariable Long id){
        return ResponseEntity.ok(computerStationService.getById(id));
    }

    @GetMapping("computer/desk/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<ComputerStationResponseDTO>> getComputerStationsByDeskId(@PathVariable Long id){
        return ResponseEntity.ok(computerStationService.getByDeskId(id));
    }

    @PostMapping("/computer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ComputerStationResponseDTO> createComputerStation(@RequestBody ComputerStationRequestDTO computerStationRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(computerStationService.createStation(computerStationRequestDTO));
    }

    @PutMapping("/computer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ComputerStationResponseDTO> updateComputer(@PathVariable Long id, @RequestBody ComputerStationRequestDTO computerStationRequestDTO){
        return ResponseEntity.ok(computerStationService.updateStation(computerStationRequestDTO,id));
    }

    @DeleteMapping("/computer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteComputer(@PathVariable Long id){
        computerStationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/computer/check/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Boolean> checkComputer(@PathVariable Long id){
        return ResponseEntity.ok(computerStationService.checkStation(id));
    }
}
