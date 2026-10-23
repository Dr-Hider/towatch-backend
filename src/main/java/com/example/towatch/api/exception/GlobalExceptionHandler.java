package com.example.towatch.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            message.append(error.getDefaultMessage()).append("|");
        });

        if (!message.isEmpty()) {
            message.deleteCharAt(message.length() - 1);
        }

        ExceptionResponse response = new ExceptionResponse(message.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
