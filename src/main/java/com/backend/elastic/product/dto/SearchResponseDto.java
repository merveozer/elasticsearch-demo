package com.backend.elastic.product.dto;

import java.util.List;

public record SearchResponseDto(
        List<ProductResponseDto> products,
        List<String> suggestedBrands
) {
    public SearchResponseDto(List<ProductResponseDto> products, List<String> suggestedBrands) {
        this.products = products;
        this.suggestedBrands = suggestedBrands;
    }
}
