package com.example.towatch.api.item.dto;

public record ItemResponse(
        Long id,
        String title,
        Long typeId,
        Long statusId
) {

}
