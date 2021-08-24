package com.microservice.booking.laboratoryroomservice.controller;

import com.microservice.booking.laboratoryroomservice.entity.Desk;
import com.microservice.booking.laboratoryroomservice.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("laboratory")
public class DeskController {

    @Autowired
    private DeskService deskService;

    @PostMapping("/desk")
    public void save(@RequestBody Desk desk){
        deskService.save(desk);
    }
}
