package com.microservice.booking.laboratoryroomservice.dataTransferObjects;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ExceptionDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ExceptionDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
