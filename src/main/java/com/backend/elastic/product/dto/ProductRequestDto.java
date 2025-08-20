package com.backend.elastic.product.dto;

public record ProductRequestDto(
        String name,
        String description,
        String category,
        int price,
        int stock,
        String brand,
        String color,
        String size,
        String ean,
        String availability,
        String currency
) {
}
