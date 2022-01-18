package exception;

import dataTransferObjects.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public interface GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    default ResponseEntity<ExceptionDTO> onException(Exception exception) {
        return createProperResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiForbiddenException.class)
    default ResponseEntity<ExceptionDTO> onForbiddenException(Exception exception) {
        return createProperResponse(exception,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiNoFoundResourceException.class)
    default ResponseEntity<ExceptionDTO> onNotFoundException(Exception exception) {
        return createProperResponse(exception,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    default ResponseEntity<ExceptionDTO> onAccessDeniedException(Exception exception) {
        return createProperResponse(exception,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiWrongParameterException.class)
    default ResponseEntity<ExceptionDTO> onApiWrongParameterException(Exception exception) {
        return createProperResponse(exception,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    default ResponseEntity<ExceptionDTO> onMethodArgumentNotValidException(Exception exception) {
        return createProperResponse(exception,HttpStatus.BAD_REQUEST);
    }

    default ResponseEntity<ExceptionDTO> createProperResponse(Exception exception, HttpStatus status) {
        exception.printStackTrace();
        return ResponseEntity.status(status).body(new ExceptionDTO(status.value(), exception.getMessage()));
    }
}