package com.example.towatch.api.type.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTypeRequest(
        @NotBlank(message = "Type name mustn't be blank")
        @Size(max = 8, message = "Type name length mustn't exceed 8 symbols")
        String name
) {

}