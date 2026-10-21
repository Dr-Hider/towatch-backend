package com.example.towatch.api.user.dto;

public record AuthResponse(
        Long id,
        String username,
        String token
) {

}
