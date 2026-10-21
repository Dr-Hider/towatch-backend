package com.example.towatch.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Username mustn't be blank")
        @Size(min = 3, max = 16, message = "Username length must be from 3 to 16 symbols")
        String username,

        @NotBlank(message = "Password mustn't be blank")
        @Size(min = 8, max = 16, message = "Password length must be from 8 to 16 symbols")
        String password
) {

}
