package com.microservice.booking.laboratoryroomservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiWrongParameterException extends RuntimeException {

    public ApiWrongParameterException(String message) {
        super(message);
    }

    public ApiWrongParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}


