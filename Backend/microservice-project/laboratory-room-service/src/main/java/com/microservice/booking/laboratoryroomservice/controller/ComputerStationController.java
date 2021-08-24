package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import com.microservice.booking.laboratoryroomservice.exception.ExceptionConst;
import com.microservice.booking.laboratoryroomservice.service.ComputerStationService;
import com.projekt.cannibal.car_rent.exceptions.ApiForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("laboratory")
public class ComputerStationController {
    @Autowired
    private ComputerStationService computerStationService;

    @PostMapping("/computer_station")
    public ComputerStation save(@RequestBody ComputerStation computerStation){
    System.out.println("STATION: " + computerStation.toString());
        return computerStationService.save(computerStation);
    }
}
