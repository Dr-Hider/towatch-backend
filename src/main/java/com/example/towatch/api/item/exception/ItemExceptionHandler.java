package com.example.towatch.api.item.exception;

import com.example.towatch.api.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleItemNotFoundException(ItemNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleItemAlreadyExistsException(ItemAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
