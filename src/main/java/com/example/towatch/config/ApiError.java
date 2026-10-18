package com.example.towatch.config;

import java.util.Map;

public record ApiError(
        int status,
        String code,
        String message,
        Map<String, String> details
) {
    public ApiError {
        details = details == null ? Map.of() : Map.copyOf(details);
    }
}
