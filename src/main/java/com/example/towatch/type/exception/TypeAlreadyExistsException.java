package com.example.towatch.type.exception;

public class TypeAlreadyExistsException extends RuntimeException {
    public TypeAlreadyExistsException(String name) {
        super("Type already exists with name: " + name);
    }
}
