package com.example.towatch.api.status.exception;

public class StatusAlreadyExistsException extends RuntimeException {
    public StatusAlreadyExistsException(String name) {
        super("Status already exists with name " + name);
    }
}
