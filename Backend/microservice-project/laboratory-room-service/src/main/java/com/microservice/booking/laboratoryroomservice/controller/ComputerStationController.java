package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.ComputerStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Computer Station API")
@RestController
@RequestMapping("laboratory")
public class ComputerStationController {

  @Autowired private ComputerStationService computerStationService;

  @ApiOperation(
      value = "Find all computer stations",
      response = ComputerStationResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/computer")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<ComputerStationResponseDTO>> getAll() {
    return ResponseEntity.ok(computerStationService.getAll());
  }

  @ApiOperation(value = "Find computer station by Id", response = ComputerStationResponseDTO.class)
  @GetMapping("/computer/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ComputerStationResponseDTO> getComputerStationById(
      @ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(computerStationService.getById(id));
  }

  @ApiOperation(
      value = "Find computer stations assigned to desk Id",
      response = ComputerStationResponseDTO.class,
      responseContainer = "List")
  @GetMapping("computer/desk/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<ComputerStationResponseDTO>> getComputerStationsByDeskId(
      @ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(computerStationService.getByDeskId(id));
  }

  @ApiOperation(value = "Create new computer station", response = ComputerStationResponseDTO.class)
  @PostMapping("/computer")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ComputerStationResponseDTO> createComputerStation(
      @Valid @RequestBody ComputerStationRequestDTO computerStationRequestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(computerStationService.createStation(computerStationRequestDTO));
  }

  @ApiOperation(
      value = "Update computer station with provided Id",
      response = ComputerStationResponseDTO.class)
  @PutMapping("/computer/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ComputerStationResponseDTO> updateComputer(
          @ApiParam(defaultValue = "1")
          @PathVariable Long id,
      @Valid @RequestBody ComputerStationRequestDTO computerStationRequestDTO) {
    return ResponseEntity.ok(computerStationService.updateStation(computerStationRequestDTO, id));
  }

  @ApiOperation(value = "Delete computer station with provided Id")
  @DeleteMapping("/computer/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> deleteComputer(@ApiParam(defaultValue = "1") @PathVariable Long id) {
    computerStationService.deleteStation(id);
    return ResponseEntity.noContent().build();
  }

  @ApiIgnore
  @GetMapping("/computer/check/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<Boolean> checkComputer(@PathVariable Long id) {
    return ResponseEntity.ok(computerStationService.checkStation(id));
  }
}
