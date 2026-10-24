package com.example.towatch.api.type.exception;

public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException() {
        super("Type not found");
    }
}
