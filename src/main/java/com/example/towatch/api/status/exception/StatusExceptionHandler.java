package com.example.towatch.api.status.exception;

import com.example.towatch.api.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StatusExceptionHandler {
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStatusNotFoundException(StatusNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(StatusAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleStatusAlreadyExistsException(StatusAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
