package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.LaboratoryRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Laboratory room API")
@RestController
@RequestMapping("laboratory")
public class LaboratoryRoomController {

  @Autowired private LaboratoryRoomService laboratoryRoomService;

  @ApiOperation(value = "Find all laboratory classrooms", response = LaboratoryRoomResponseDTO.class, responseContainer = "List")
  @GetMapping("/room")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<LaboratoryRoomResponseDTO>> getAll() {
    return ResponseEntity.ok(laboratoryRoomService.getAll());
  }

  @ApiOperation(value = "Find laboratory classroom by Id", response = LaboratoryRoomResponseDTO.class)
  @GetMapping("/room/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<LaboratoryRoomResponseDTO> getRoomById(@ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(laboratoryRoomService.getRoomById(id));
  }

  @ApiOperation(value = "Create new laboratory classroom", response = LaboratoryRoomResponseDTO.class)
  @PostMapping("/room")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<LaboratoryRoomResponseDTO> createLaboratoryRoom(
      @Valid @RequestBody LaboratoryRoomRequestDTO laboratoryRoomRequestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(laboratoryRoomService.createLaboratoryRoom(laboratoryRoomRequestDTO));
  }

  @ApiOperation(value = "Update laboratory classroom with provided Id", response = LaboratoryRoomResponseDTO.class)
  @PutMapping("/room/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<LaboratoryRoomResponseDTO> updateRoomById(
          @ApiParam(defaultValue = "1") @PathVariable Long id,
      @Valid @RequestBody LaboratoryRoomRequestDTO laboratoryRoomRequestDTO) {
    return ResponseEntity.ok(
        laboratoryRoomService.updateLaboratoryRoom(laboratoryRoomRequestDTO, id));
  }

  @ApiOperation(value = "Delete laboratory classroom with provided Id")
  @DeleteMapping("/room/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> deleteRoomById( @ApiParam(defaultValue = "1") @PathVariable Long id) {
    laboratoryRoomService.deleteLaboratoryRoom(id);
    return ResponseEntity.noContent().build();
  }
}
