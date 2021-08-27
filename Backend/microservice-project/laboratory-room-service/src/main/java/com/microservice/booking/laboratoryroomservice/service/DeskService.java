package com.microservice.booking.laboratoryroomservice.service;

import com.microservice.booking.laboratoryroomservice.entity.Desk;
import com.microservice.booking.laboratoryroomservice.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeskService {

    @Autowired
    private DeskRepository deskRepository;

    public void save(Desk desk){
        deskRepository.save((desk));
    }
}
