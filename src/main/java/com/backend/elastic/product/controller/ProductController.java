package com.backend.elastic.product.controller;

import com.backend.elastic.product.dto.ProductRequestDto;
import com.backend.elastic.product.dto.ProductResponseDto;
import com.backend.elastic.product.dto.SearchResponseDto;
import com.backend.elastic.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<SearchResponseDto> getProductWithNameAndBrandSuggestions(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductWithNameAndBrandSuggestions(name));
    }
}
