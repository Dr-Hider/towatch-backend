package com.example.towatch.api.type.exception;

import com.example.towatch.api.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TypeExceptionHandler {
    @ExceptionHandler(TypeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTypeNotFound(TypeNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TypeAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTypeAlreadyExists(TypeAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
