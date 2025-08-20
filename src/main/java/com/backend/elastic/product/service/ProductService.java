package com.backend.elastic.product.service;

import com.backend.elastic.product.dto.ProductRequestDto;
import com.backend.elastic.product.dto.ProductResponseDto;
import com.backend.elastic.product.dto.SearchResponseDto;


public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    SearchResponseDto getProductWithNameAndBrandSuggestions(String name);
}
