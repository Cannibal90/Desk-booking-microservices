package com.microservice.booking.userservice.exceptions;

import exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHandler implements GlobalExceptionHandler {
}
