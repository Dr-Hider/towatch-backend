package com.example.towatch.api.user.exception;

import com.example.towatch.api.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidLoginOrPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidLoginOrPassword(InvalidLoginOrPasswordException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFound(UserNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
