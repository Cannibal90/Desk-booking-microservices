package com.microservice.booking.reservationservice.service;

import com.microservice.booking.reservationservice.dataTransferObjects.ReservationRequestDTO;
import com.microservice.booking.reservationservice.dataTransferObjects.ReservationResponseDTO;
import com.microservice.booking.reservationservice.entity.Durration;
import com.microservice.booking.reservationservice.entity.Reservation;
import com.microservice.booking.reservationservice.mapper.ReservationMapper;
import com.microservice.booking.reservationservice.repository.ReservationRepository;
import exception.ApiNoFoundResourceException;
import exception.ApiWrongParameterException;
import exception.ExceptionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

  @Autowired private ReservationRepository reservationRepository;
  @Autowired ReservationMapper reservationMapper;
  @Autowired RestTemplate restTemplate;

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

  public List<ReservationResponseDTO> getReservationsForComputerId(Long id) {
    if(!validateStation(id))
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_COMPUTER);
    List<Reservation> reservations = reservationRepository.findReservationsByStationId(id);
    List<ReservationResponseDTO> responseDTO = new ArrayList<>();
    responseDTO.addAll(reservationMapper.toReservationResponseList(reservations));
    return  responseDTO;
  }

  public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
    return new ReservationResponseDTO();
  }

  public ReservationResponseDTO updateReservation(
      ReservationRequestDTO reservationRequestDTO, Long id) {
    return new ReservationResponseDTO();
  }

  public void deleteReservation(Long id) {
    if (id <= 0) throw new ApiWrongParameterException(ExceptionConst.WRONG_PARAMETER);

    if (reservationRepository.findById(id).isEmpty())
      throw new ApiNoFoundResourceException(ExceptionConst.NOT_FOUND_RESERVATION);
    reservationRepository.deleteById(id);
  }

  private boolean validateDurration(Durration durration1, Durration durration2) {
    if (durration1.getEnd().isAfter(durration2.getBeginning())
        || durration1.getBeginning().isBefore(durration2.getEnd())
        || (durration1.getBeginning().isBefore(durration2.getBeginning())
            && durration1.getEnd().isAfter(durration2.getEnd()))
        || (durration1.getBeginning().isAfter(durration2.getBeginning())
            && durration1.getEnd().isBefore(durration2.getEnd()))) return true;
    return false;
  }

  private boolean validateUser(Long id) {
    String userURI = "http://localhost:8989/users/user/check/" + id;
    Boolean check = restTemplate.getForObject(userURI,Boolean.class);
    return check;
  }

  private boolean validateStation(Long id) {
    String stationURI = "http://localhost:8200/laboratory/computer/check/" + id;
    Boolean check = restTemplate.getForObject(stationURI,Boolean.class);
    return check;
  }

}
