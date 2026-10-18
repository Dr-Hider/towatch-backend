package com.example.towatch.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username mustn't be blank")
        @Size(min = 3, max = 16, message = "Username length must be from 3 to 16 symbols")
        @Pattern(
                regexp = "[a-zA-Z0-9_]+",
                message = "Username mustn't contain anything but Latin letters, numbers from 0 to 9, underscores"
        )
        String username,

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 8, max = 72, message = "Password length must be from 8 to 72 symbols")
        String password
) {

}
