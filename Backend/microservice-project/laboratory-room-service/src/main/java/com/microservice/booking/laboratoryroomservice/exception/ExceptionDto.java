package com.microservice.booking.laboratoryroomservice.exception;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ExceptionDto {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ExceptionDto(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
