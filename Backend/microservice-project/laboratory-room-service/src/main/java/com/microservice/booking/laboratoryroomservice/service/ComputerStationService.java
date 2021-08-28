package com.microservice.booking.laboratoryroomservice.service;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ComputerStationResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.ComputerStation;
import com.microservice.booking.laboratoryroomservice.exception.ApiNoFoundResourceException;
import com.microservice.booking.laboratoryroomservice.exception.ApiWrongParameterException;
import com.microservice.booking.laboratoryroomservice.exception.ExceptionConst;
import com.microservice.booking.laboratoryroomservice.mappers.ComputerStationMapper;
import com.microservice.booking.laboratoryroomservice.repository.ComputerStationRepository;
import com.microservice.booking.laboratoryroomservice.repository.DeskRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerStationService {

    @Autowired
    private ComputerStationRepository computerStationRepository;
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private ComputerStationMapper computerStationMapper;

    public List<ComputerStationResponseDTO> getAll() {
        List<ComputerStation> computerStations = computerStationRepository.findAll();
        List<ComputerStationResponseDTO> computerDTO = new ArrayList<>();
        computerDTO.addAll(computerStationMapper.toComputerStationResponseList(computerStations));
        return  computerDTO;
    }

    public ComputerStationResponseDTO getById(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var computer = computerStationRepository.findById(id);
        if(computer.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);

        return computerStationMapper.toComputerStationResponse(computer.get());
    }

    public List<ComputerStationResponseDTO> getByDeskId(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var desk = deskRepository.findById(id);
        if(desk.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);

        var computers = desk.get().getComputerStations();
        return computerStationMapper.toComputerStationResponseList(computers);
    }

    public ComputerStationResponseDTO createStation(ComputerStationRequestDTO computerStationRequestDTO) {
        if( computerStationRequestDTO == null || computerStationRequestDTO.getDeskId() <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var desk = deskRepository.findById(computerStationRequestDTO.getDeskId());
        if(desk.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);

        var computer = computerStationMapper.toDomain(computerStationRequestDTO);
        var savedComputer = computerStationRepository.save(computer);

        desk.get().getComputerStations().add(savedComputer);
        deskRepository.save(desk.get());
        return computerStationMapper.toComputerStationResponse(savedComputer);
    }

    public ComputerStationResponseDTO updateStation(ComputerStationRequestDTO computerStationRequestDTO, Long id){
        if(computerStationRequestDTO == null || computerStationRequestDTO.getDeskId() <= 0 || id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var desk = deskRepository.findById(computerStationRequestDTO.getDeskId());
        if(desk.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);

        var computer = computerStationRepository.findById(id);
        if(computer.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);


        var updatedComputerStation = computerStationMapper.toDomain(computerStationRequestDTO);
        updatedComputerStation.setId(id);

        var savedComputerStation = computerStationRepository.save(updatedComputerStation);
        var filteredDeskComputerList = desk
                .get()
                .getComputerStations()
                .stream()
                .filter(c -> c.getId() != id)
                .collect(Collectors.toList());
        filteredDeskComputerList.add(savedComputerStation);
        desk.get().setComputerStations(filteredDeskComputerList);
        deskRepository.save(desk.get());
        return computerStationMapper.toComputerStationResponse(savedComputerStation);
    }

    public void deleteStation(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        if(computerStationRepository.findById(id).isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);
        computerStationRepository.deleteById(id);
    }
}
