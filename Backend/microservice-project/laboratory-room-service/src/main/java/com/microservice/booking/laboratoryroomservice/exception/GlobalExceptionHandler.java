package com.microservice.booking.laboratoryroomservice.exception;

import com.microservice.booking.laboratoryroomservice.dataTransferObjects.ExceptionDTO;
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
    public ResponseEntity<ExceptionDTO> onException(Exception exception) {
        return createProperResponse(exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiForbiddenException.class)
    public ResponseEntity<ExceptionDTO> onForbiddenException(Exception exception) {
        return createProperResponse(exception,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiNoFoundResourceException.class)
    public ResponseEntity<ExceptionDTO> onNotFoundException(Exception exception) {
        return createProperResponse(exception,HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ExceptionDTO> createProperResponse(Exception exception, HttpStatus status) {
        exception.printStackTrace();
        return ResponseEntity.status(status).body(new ExceptionDTO(status.value(), exception.getMessage()));
    }
}
