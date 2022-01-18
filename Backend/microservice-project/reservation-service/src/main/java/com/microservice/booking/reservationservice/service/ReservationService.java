package com.microservice.booking.reservationservice.service;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import com.microservice.booking.reservationservice.mapper.ReservationMapper;
import com.microservice.booking.reservationservice.repository.DurrationRepository;
import com.microservice.booking.reservationservice.repository.ReservationRepository;
import exception.ApiForbiddenException;
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
import validators.OwnResourceValidator;
import web.AppUser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

  @Autowired private ReservationRepository reservationRepository;
  @Autowired ReservationMapper reservationMapper;
  @Autowired RestTemplate restTemplate;
  @Autowired DurrationRepository durrationRepository;

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

  public List<ReservationResponseDTO> getReservationByUserId(Long id) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    var reservation = reservationRepository.findAllByUserId(id);

    return reservationMapper.toReservationResponseList(reservation);
  }

  public List<ReservationResponseDTO> getReservationsForComputerId(Long id, String auth) {
    if (!validateStation(id, auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);
    List<Reservation> reservations = reservationRepository.findAllByStationId(id);
    List<ReservationResponseDTO> responseDTO = new ArrayList<>();
    responseDTO.addAll(reservationMapper.toReservationResponseList(reservations));
    return responseDTO;
  }

  public ReservationResponseDTO createReservation(
      ReservationRequestDTO reservationRequestDTO, String auth, AppUser appUser) {
    if (!validateUser(reservationRequestDTO.getUserId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

    if (!validateStation(reservationRequestDTO.getStationId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);

    OwnResourceValidator.validate(appUser, reservationRequestDTO.getUserId());

    Durration requestDurration = new Durration();
    requestDurration.setBeginning(reservationRequestDTO.getBeginning());
    requestDurration.setEnd(reservationRequestDTO.getEnd());
    validateRequestDurration(requestDurration, appUser);

    var durrations = durrationRepository.findAllByStationId(reservationRequestDTO.getStationId());
    boolean checkDurration = false;
    for (Durration durration : durrations) {
      if (!validateTwoDurrations(durration, requestDurration)) {
        checkDurration = true;
        break;
      }
    }

    if (checkDurration) throw new ApiWrongParameterException(ExceptionConst.WRONG_RESERVATION);

    var reservation = reservationMapper.toDomain(reservationRequestDTO);
    var saved = reservationRepository.save(reservation);
    return reservationMapper.toReservationResponse(saved);
  }

  public ReservationResponseDTO updateReservation(
      ReservationRequestDTO reservationRequestDTO, Long id, String auth, AppUser appUser) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    var reservationInDb = reservationRepository.findById(id);
    if (reservationInDb.isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);

    OwnResourceValidator.validate(appUser, reservationInDb.get().getUserId());

    if (!validateUser(reservationRequestDTO.getUserId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_USER);

    if (!validateStation(reservationRequestDTO.getStationId(), auth))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);

    OwnResourceValidator.validate(appUser, reservationRequestDTO.getUserId());

    Durration requestDurration = new Durration();
    requestDurration.setBeginning(reservationRequestDTO.getBeginning());
    requestDurration.setEnd(reservationRequestDTO.getEnd());
    validateRequestDurration(requestDurration, appUser);

    var durrations = durrationRepository.findAllByStationId(reservationRequestDTO.getStationId());
    var filtered =
        durrations.stream()
            .filter(d -> d.getId() != reservationInDb.get().getDurration().getId())
            .collect(Collectors.toList());
    boolean checkDurration = false;
    for (Durration durration : filtered) {
      if (!validateTwoDurrations(durration, requestDurration)) {
        checkDurration = true;
        break;
      }
    }
    if (checkDurration) throw new ApiWrongParameterException(ExceptionConst.WRONG_RESERVATION);

    var reservation = reservationMapper.toDomain(reservationRequestDTO);
    reservation.setId(reservationInDb.get().getId());
    reservation.getDurration().setId(reservationInDb.get().getDurration().getId());
    var saved = reservationRepository.save(reservation);
    return reservationMapper.toReservationResponse(saved);
  }

  public void deleteReservation(Long id, AppUser appUser) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    var reservation = reservationRepository.findById(id);
    if (reservation.isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);
    OwnResourceValidator.validate(appUser, reservation.get().getUserId());
    reservationRepository.deleteById(id);
  }

  public void deleteReservationsForUser(AppUser appUser) {
    if (appUser.getId() <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);
    var reservations = reservationRepository.findAllByUserId(appUser.getId());
    reservations.forEach(reservation -> reservationRepository.deleteById(reservation.getId()));
  }

  private void validateRequestDurration(Durration durration, AppUser appUser){
    if (durration.getBeginning().isAfter(durration.getEnd()))
      throw new ApiWrongParameterException(ExceptionConst.WRONG_RESERVATION);

    if(!appUser.getRole().equals("ROLE_ADMIN") && Duration.between(durration.getBeginning(), durration.getEnd()).toHours() > 4)
      throw new ApiForbiddenException(ExceptionConst.NOT_ALLOWED);
  }

  private boolean validateTwoDurrations(Durration durration1, Durration durration2) {

    if ((durration1.getEnd().isAfter(durration2.getBeginning())
            && (durration1.getEnd().isBefore(durration2.getEnd())))
        || (durration1.getBeginning().isBefore(durration2.getEnd())
            && durration1.getEnd().isAfter(durration2.getEnd()))
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
    ResponseEntity<Boolean> response =
        restTemplate.exchange(userURI, HttpMethod.GET, entity, Boolean.class);
    return response.getBody() != null;
  }

  private boolean validateStation(Long id, String auth) {
    String stationURI = "http://localhost:8200/laboratory/computer/check/" + id;
    HttpEntity<String> entity = getEntity(auth);
    ResponseEntity<Boolean> response =
        restTemplate.exchange(stationURI, HttpMethod.GET, entity, Boolean.class);
    return response.getBody() != null;
  }

}
