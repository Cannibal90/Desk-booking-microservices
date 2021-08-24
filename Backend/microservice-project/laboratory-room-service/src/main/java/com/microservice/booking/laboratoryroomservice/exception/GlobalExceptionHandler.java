package com.microservice.booking.laboratoryroomservice.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.projekt.cannibal.car_rent.exceptions.ApiForbiddenException;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception) {
        return createProperResponse(exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiForbiddenException.class)
    public ResponseEntity<ExceptionDto> onForbiddenException(Exception exception) {
        return createProperResponse(exception,HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ExceptionDto> createProperResponse(Exception exception, HttpStatus status) {
        exception.printStackTrace();
        return ResponseEntity.status(status).body(new ExceptionDto(status.value(), exception.getMessage()));
    }
}
