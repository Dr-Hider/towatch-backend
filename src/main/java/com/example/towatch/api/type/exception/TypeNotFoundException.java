package com.example.towatch.api.type.exception;

public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException(Long id) {
        super("Type not found with id: " + id);
    }
}
