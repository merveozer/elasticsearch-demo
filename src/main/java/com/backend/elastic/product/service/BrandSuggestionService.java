package com.backend.elastic.product.service;

import java.util.List;

public interface BrandSuggestionService {
    List<String> getBrandsByCategory(String category);
}
