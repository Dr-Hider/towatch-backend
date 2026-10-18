package com.example.towatch.user.dto;

public record AuthResponse(
        Long id,
        String username,
        String role
) {

}