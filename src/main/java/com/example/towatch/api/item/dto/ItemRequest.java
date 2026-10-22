package com.example.towatch.api.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemRequest(
        @NotBlank(message = "Title mustn't be blank")
        @Size(max = 32, message = "Title length mustn't exceed 32 symbols")
        String title,

        @NotNull(message = "Type ID mustn't be blank")
        Long typeId,

        @NotNull(message = "Status ID mustn't be blank")
        Long statusId
) {

}
