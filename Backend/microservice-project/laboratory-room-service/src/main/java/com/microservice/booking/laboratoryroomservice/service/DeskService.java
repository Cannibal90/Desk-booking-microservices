package com.microservice.booking.laboratoryroomservice.service;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.DeskResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.Desk;
import com.microservice.booking.laboratoryroomservice.mappers.DeskMapper;
import com.microservice.booking.laboratoryroomservice.repository.DeskRepository;
import com.microservice.booking.laboratoryroomservice.repository.LaboratoryRoomRepository;
import exception.ApiNoFoundResourceException;
import exception.ApiWrongParameterException;
import exception.ExceptionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeskService {

    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private LaboratoryRoomRepository laboratoryRoomRepository;
    @Autowired
    private DeskMapper deskMapper;


    public List<DeskResponseDTO> getAll() {
        List<Desk> desks = deskRepository.findAll();
        List<DeskResponseDTO> desksDTO = new ArrayList<>();
        desksDTO.addAll(deskMapper.toDeskResponseList(desks));
        return desksDTO;
    }

    public DeskResponseDTO getById(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var desk = deskRepository.findById(id);

        if(desk.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);

        return deskMapper.toDeskResponse(desk.get());
    }

    public List<DeskResponseDTO> getByRoomId(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var room = laboratoryRoomRepository.findById(id);
        if(room.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_ROOM);

        var desks = room.get().getDesks();
        return deskMapper.toDeskResponseList(desks);
    }

    public DeskResponseDTO createDesk(DeskRequestDTO deskRequestDTO){
        if( deskRequestDTO == null || deskRequestDTO.getRoomId() <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var room = laboratoryRoomRepository.findById(deskRequestDTO.getRoomId());
        if(room.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_ROOM);

        var desk = deskMapper.toDomain(deskRequestDTO);
        var savedDesk = deskRepository.save(desk);

        room.get().getDesks().add(savedDesk);
        laboratoryRoomRepository.save(room.get());
        return deskMapper.toDeskResponse(savedDesk);
    }


    public DeskResponseDTO updateDesk(DeskRequestDTO deskRequestDTO, Long id) {
        if( deskRequestDTO == null || deskRequestDTO.getRoomId() <= 0 || id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var room = laboratoryRoomRepository.findById(deskRequestDTO.getRoomId());
        if(room.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_ROOM);

        var desk = deskRepository.findById(id);
        if(desk.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);

        var updatedDesk = deskMapper.toDomain(deskRequestDTO);
        updatedDesk.setId(desk.get().getId());
        updatedDesk.setComputerStations(desk.get().getComputerStations());

        var savedDesk = deskRepository.save(updatedDesk);
        var filtereddRoomDeskList = room
                .get()
                .getDesks()
                .stream()
                .filter(r-> r.getId() != id)
                .collect(Collectors.toList());
        filtereddRoomDeskList.add(savedDesk);
        room.get().setDesks(filtereddRoomDeskList);
        laboratoryRoomRepository.save(room.get());
        return deskMapper.toDeskResponse(savedDesk);
    }

    public void deleteDeskById(Long id) {
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        if(deskRepository.findById(id).isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_DESK);
        deskRepository.deleteById(id);
    }
}
