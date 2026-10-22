package com.example.towatch.api.status.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StatusRequest(
        @NotBlank(message = "Status mustn't be blank")
        @Size(max = 8, message = "Status length mustn't exceed 8 symbols")
        String name
) {

}
