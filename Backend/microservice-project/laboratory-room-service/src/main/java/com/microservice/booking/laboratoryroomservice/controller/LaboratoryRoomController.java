package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.LaboratoryRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
