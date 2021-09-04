package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.ComputerStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class ComputerStationController {
    @Autowired
    private ComputerStationService computerStationService;

    //TODO napisac funkcje i potestowac
    //TODO rozpisac logike frontu dla dodawania i usuwania room/desk/computer

    @GetMapping("/computer")
    public ResponseEntity<List<ComputerStationResponseDTO>> getAll(){
        return ResponseEntity.ok(computerStationService.getAll());
    }

    @GetMapping("/computer/{id}")
    public ResponseEntity<ComputerStationResponseDTO> getComputerStationById(@PathVariable Long id){
        return ResponseEntity.ok(computerStationService.getById(id));
    }

    @GetMapping("computer/desk/{id}")
    public ResponseEntity<List<ComputerStationResponseDTO>> getComputerStationsByDeskId(@PathVariable Long id){
        return ResponseEntity.ok(computerStationService.getByDeskId(id));
    }

    @PostMapping("/computer")
    public ResponseEntity<ComputerStationResponseDTO> createComputerStation(@RequestBody ComputerStationRequestDTO computerStationRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(computerStationService.createStation(computerStationRequestDTO));
    }

    @PutMapping("/computer/{id}")
    public ResponseEntity<ComputerStationResponseDTO> updateComputer(@PathVariable Long id, @RequestBody ComputerStationRequestDTO computerStationRequestDTO){
        return ResponseEntity.ok(computerStationService.updateStation(computerStationRequestDTO,id));
    }

    @DeleteMapping("/computer/{id}")
    public ResponseEntity<?> deleteComputer(@PathVariable Long id){
        computerStationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
}
