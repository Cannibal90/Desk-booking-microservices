package com.microservice.booking.reservationservice.service;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import com.microservice.booking.reservationservice.mapper.ReservationMapper;
import com.microservice.booking.reservationservice.repository.DurrationRepository;
import com.microservice.booking.reservationservice.repository.ReservationRepository;
import exception.ApiNoFoundResourceException;
import exception.ApiWrongParameterException;
import exception.ExceptionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

  @Autowired private ReservationRepository reservationRepository;
  @Autowired ReservationMapper reservationMapper;
  @Autowired RestTemplate restTemplate;
  @Autowired
  DurrationRepository durrationRepository;

  public List<ReservationResponseDTO> getAll() {
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponseDTO> responseDTO = new ArrayList<>();
    responseDTO.addAll(reservationMapper.toReservationResponseList(reservations));
    return responseDTO;
  }

  public ReservationResponseDTO getReservationById(Long id) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    var reservation = reservationRepository.findById(id);
    if (reservation.isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);

    return reservationMapper.toReservationResponse(reservation.get());
  }

  public List<ReservationResponseDTO> getReservationsForComputerId(Long id, String auth) {
    if(!validateStation(id, auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);
    List<Reservation> reservations = reservationRepository.findAllByStationId(id);
    List<ReservationResponseDTO> responseDTO = new ArrayList<>();
    responseDTO.addAll(reservationMapper.toReservationResponseList(reservations));
    return  responseDTO;
  }

  public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO, String auth) {
    if(!validateUser(reservationRequestDTO.getUserId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

    if(!validateStation(reservationRequestDTO.getStationId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);

    // check all durration for desk
    Durration requestDurration = new Durration();
    requestDurration.setBeginning(reservationRequestDTO.getBeginning());
    requestDurration.setEnd(reservationRequestDTO.getEnd());
    var durrations = durrationRepository.findAllByStationId(reservationRequestDTO.getStationId());
    boolean checkDurration = false;
    for(Durration durration: durrations){
      if(!validateDurration( durration, requestDurration)) {
        checkDurration = true;
        break;
      }
    }

    if(checkDurration)
      throw new ApiWrongParameterException(ExceptionConst.WRONG_RESERVATION);

    var reservation = reservationMapper.toDomain(reservationRequestDTO);
    var saved = reservationRepository.save(reservation);
    return  reservationMapper.toReservationResponse(saved);
  }

  public ReservationResponseDTO updateReservation(
      ReservationRequestDTO reservationRequestDTO, Long id, String auth) {
    if(id <= 0)
      throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    var reservationInDb = reservationRepository.findById(id);
    if(reservationInDb.isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);

    if(!validateUser(reservationRequestDTO.getUserId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

    if(!validateStation(reservationRequestDTO.getStationId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);


    //check all durration for desk
    Durration requestDurration = new Durration();
    requestDurration.setBeginning(reservationRequestDTO.getBeginning());
    requestDurration.setEnd(reservationRequestDTO.getEnd());
    var durrations = durrationRepository.findAllByStationId(reservationRequestDTO.getStationId());
    var filtered = durrations.stream().filter(d->d.getId()!=reservationInDb.get().getDurration().getId()).collect(Collectors.toList());
    boolean checkDurration = false;
    for(Durration durration: filtered){
      if(!validateDurration(durration, requestDurration)) {
        checkDurration = true;
        break;
      }
    }
    if(checkDurration)
      throw new ApiWrongParameterException(ExceptionConst.WRONG_RESERVATION);

    var reservation = reservationMapper.toDomain(reservationRequestDTO);
    reservation.setId(reservationInDb.get().getId());
    reservation.getDurration().setId(reservationInDb.get().getDurration().getId());
    var saved = reservationRepository.save(reservation);
    return  reservationMapper.toReservationResponse(saved);

  }

  public void deleteReservation(Long id) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    if (reservationRepository.findById(id).isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);
    reservationRepository.deleteById(id);
  }

  private boolean validateDurration(Durration durration1, Durration durration2) {
    //TODO max 4h dla usera i czy czas w d1 jest beg przed end
    if ((durration1.getEnd().isAfter(durration2.getBeginning()) && (durration1.getEnd().isBefore(durration2.getEnd())))
        || (durration1.getBeginning().isBefore(durration2.getEnd()) && durration1.getEnd().isAfter(durration2.getEnd()))
        || (durration1.getBeginning().isBefore(durration2.getBeginning())
            && durration1.getEnd().isAfter(durration2.getEnd()))
        || (durration1.getBeginning().isAfter(durration2.getBeginning())
            && durration1.getEnd().isBefore(durration2.getEnd()))) return false;
    return true;
  }

  private HttpEntity<String> getEntity(String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", auth);
    return new HttpEntity<String>(headers);
  }

  private boolean validateUser(Long id, String auth) {
    String userURI = "http://localhost:8989/users/user/check/" + id;
    HttpEntity<String> entity = getEntity(auth);
    ResponseEntity<Boolean> response = restTemplate.exchange(userURI, HttpMethod.GET, entity, Boolean.class);
    return response.getBody() != null;
  }

  private boolean validateStation(Long id, String auth) {
    String stationURI = "http://localhost:8200/laboratory/computer/check/" + id;
    HttpEntity<String> entity = getEntity(auth);
    ResponseEntity<Boolean> response = restTemplate.exchange(stationURI, HttpMethod.GET, entity, Boolean.class);
    return response.getBody() != null;
  }

}
