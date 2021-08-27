package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.LaboratoryRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class LaboratoryRoomController {

    @Autowired
    private LaboratoryRoomService laboratoryRoomService;

    @GetMapping("/room")
    public ResponseEntity<List<LaboratoryRoomResponseDTO>> getAll() {
        return ResponseEntity.ok(laboratoryRoomService.getAll());
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<LaboratoryRoomResponseDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoryRoomService.getRoomById(id));
    }

    @PostMapping("/room")
    public ResponseEntity<LaboratoryRoomResponseDTO> createLaboratoryRoom(@RequestBody LaboratoryRoomRequestDTO laboratoryRoomRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratoryRoomService.createLaboratoryRoom(laboratoryRoomRequestDTO));
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<LaboratoryRoomResponseDTO> updateRoomById(@PathVariable Long id, @RequestBody LaboratoryRoomRequestDTO laboratoryRoomRequestDTO){
        return ResponseEntity.ok(laboratoryRoomService.updateLaboratoryRoom(laboratoryRoomRequestDTO,id));
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable Long id) {
        laboratoryRoomService.deleteLaboratoryRoom(id);
        return ResponseEntity.noContent().build();
    }
}
