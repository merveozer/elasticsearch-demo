package com.backend.elastic.config.dto;

import java.util.Map;

public record ConfigRuleRequestDto(
        String name,
        Map<String, Object> body
) {
}
