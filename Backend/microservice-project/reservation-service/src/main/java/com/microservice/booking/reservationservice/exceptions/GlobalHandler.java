package com.microservice.booking.reservationservice.exceptions;

import exception.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalHandler implements GlobalExceptionHandler {
}
