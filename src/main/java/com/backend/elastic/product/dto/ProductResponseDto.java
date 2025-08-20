package com.backend.elastic.product.dto;

public record ProductResponseDto(
        Object productRequestDto,
        String message
) {
}
