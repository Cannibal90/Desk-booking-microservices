package com.microservice.booking.laboratoryroomservice.service;

import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import com.microservice.booking.laboratoryroomservice.repository.ComputerStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerStationService {

    @Autowired
    private ComputerStationRepository computerStationRepository;

    public ComputerStation save(ComputerStation computerStation) {
        return computerStationRepository.save(computerStation);
    }
}
