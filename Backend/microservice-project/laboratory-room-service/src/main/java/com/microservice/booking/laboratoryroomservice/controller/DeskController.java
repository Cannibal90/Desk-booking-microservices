package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskResponseDTO;
import com.microservice.booking.laboratoryroomservice.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class DeskController {

    @Autowired
    private DeskService deskService;

    @GetMapping("/desk")
    public ResponseEntity<List<DeskResponseDTO>> getAll(){
        return ResponseEntity.ok(deskService.getAll());
    }

    @GetMapping("/desk/{id}")
    public ResponseEntity<DeskResponseDTO> getDeskById(@PathVariable Long id){
        return ResponseEntity.ok(deskService.getById(id));
    }

    @GetMapping("/desk/room/{id}")
    public ResponseEntity<List<DeskResponseDTO>> getDesksByRoomId(@PathVariable Long id){
        return ResponseEntity.ok(deskService.getByRoomId(id));
    }

    @PostMapping("/desk")
    public ResponseEntity<DeskResponseDTO> saveDesk(@RequestBody DeskRequestDTO deskRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(deskService.createDesk(deskRequestDTO));
    }

    @PutMapping("/desk/{id}")
    public ResponseEntity<DeskResponseDTO> updateDesk(@PathVariable Long id, @RequestBody DeskRequestDTO deskRequestDTO){
        return ResponseEntity.ok(deskService.updateDesk(deskRequestDTO, id));
    }

    @DeleteMapping("/desk/{id}")
    public ResponseEntity<?> deleteDeskById(@PathVariable Long id){
        deskService.deleteDeskById(id);
        return ResponseEntity.noContent().build();
    }
}
