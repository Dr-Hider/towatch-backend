package com.example.towatch.api.user.exception;

public class InvalidLoginOrPasswordException extends RuntimeException {
    public InvalidLoginOrPasswordException() {
        super("Invalid login or password");
    }
}
