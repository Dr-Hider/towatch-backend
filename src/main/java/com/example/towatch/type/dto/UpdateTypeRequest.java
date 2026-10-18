package com.example.towatch.type.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTypeRequest(
        @NotBlank(message = "Название типа не может быть пустым")
        @Size(max = 8, message = "Название типа не может содержать больше 8 символов")
        String name
) {

}
