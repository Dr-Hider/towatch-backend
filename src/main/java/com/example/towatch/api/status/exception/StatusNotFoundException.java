package com.example.towatch.api.status.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(Long id) {
        super("Status not found with id " + id);
    }
}
