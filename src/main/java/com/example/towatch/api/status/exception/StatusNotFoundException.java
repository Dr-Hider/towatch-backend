package com.example.towatch.api.status.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException() {
        super("Status not found");
    }
}
