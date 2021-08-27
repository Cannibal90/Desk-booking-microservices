package com.microservice.booking.laboratoryroomservice.service;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomRequestDTO;
import com.microservice.booking.laboratoryroomservice.dataTransferObjects.LaboratoryRoomResponseDTO;
import com.microservice.booking.laboratoryroomservice.entity.LaboratoryRoom;
import com.microservice.booking.laboratoryroomservice.exception.ApiNoFoundResourceException;
import com.microservice.booking.laboratoryroomservice.exception.ApiWrongParameterException;
import com.microservice.booking.laboratoryroomservice.exception.ExceptionConst;
import com.microservice.booking.laboratoryroomservice.mappers.LaboratoryRoomMapper;
import com.microservice.booking.laboratoryroomservice.repository.LaboratoryRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaboratoryRoomService {

    @Autowired
    private LaboratoryRoomRepository laboratoryRoomRepository;
    @Autowired
    private LaboratoryRoomMapper laboratoryRoomMapper;

    public List<LaboratoryRoomResponseDTO> getAll(){
        List<LaboratoryRoom> rooms = laboratoryRoomRepository.findAll();
        List<LaboratoryRoomResponseDTO> roomsDTO = new ArrayList<>();
        roomsDTO.addAll(laboratoryRoomMapper.toLaboratoryRoomResponseList(rooms));
        return roomsDTO;
    }

    public LaboratoryRoomResponseDTO getRoomById(Long id){
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        var room = laboratoryRoomRepository.findById(id);

        if(room.isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_ROOM);

        return laboratoryRoomMapper.toLaboratoryRoomResponse(room.get());
    }

    public void deleteLaboratoryRoom(Long id){
        if(id <= 0)
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

        if(laboratoryRoomRepository.findById(id).isEmpty())
            throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_ROOM);
        laboratoryRoomRepository.deleteById(id);
    }

    public LaboratoryRoomResponseDTO createLaboratoryRoom(LaboratoryRoomRequestDTO laboratoryRoomRequestDTO){
        if(laboratoryRoomRequestDTO.getFloor() <= 0 || laboratoryRoomRequestDTO.getRoomSupervisor().isEmpty())
            throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);
        var room = laboratoryRoomMapper.toDomain(laboratoryRoomRequestDTO);
        var savedRoom = laboratoryRoomRepository.save(room);
        return laboratoryRoomMapper.toLaboratoryRoomResponse(savedRoom);
    }

}
