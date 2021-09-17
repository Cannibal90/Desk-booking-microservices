package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.configuration.SwaggerConfiguration;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.DeskService;
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


@RestController
@RequestMapping("laboratory")
@Api(tags = {SwaggerConfiguration.DESK_TAG})
public class DeskController {

  @Autowired private DeskService deskService;

  @ApiOperation(
      value = "Find all desks",
      response = DeskResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/desk")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<DeskResponseDTO>> getAll() {
    return ResponseEntity.ok(deskService.getAll());
  }

  @ApiOperation(value = "Find desk by Id", response = DeskResponseDTO.class)
  @GetMapping("/desk/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<DeskResponseDTO> getDeskById(
      @ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(deskService.getById(id));
  }

  @ApiOperation(
      value = "Find all desks by room Id",
      response = DeskResponseDTO.class,
      responseContainer = "List")
  @GetMapping("/desk/room/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<List<DeskResponseDTO>> getDesksByRoomId(
      @ApiParam(defaultValue = "1") @PathVariable Long id) {
    return ResponseEntity.ok(deskService.getByRoomId(id));
  }

  @ApiOperation(value = "Create new desk", response = DeskResponseDTO.class)
  @PostMapping("/desk")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<DeskResponseDTO> saveDesk(
      @Valid @RequestBody DeskRequestDTO deskRequestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(deskService.createDesk(deskRequestDTO));
  }

  @ApiOperation(value = "Update desk with provided Id", response = DeskResponseDTO.class)
  @PutMapping("/desk/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<DeskResponseDTO> updateDesk(
      @ApiParam(defaultValue = "1") @PathVariable Long id,
      @Valid @RequestBody DeskRequestDTO deskRequestDTO) {
    return ResponseEntity.ok(deskService.updateDesk(deskRequestDTO, id));
  }

  @ApiOperation(value = "Delete desk with provided Id")
  @DeleteMapping("/desk/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> deleteDeskById(@ApiParam(defaultValue = "1") @PathVariable Long id) {
    deskService.deleteDeskById(id);
    return ResponseEntity.noContent().build();
  }
}
