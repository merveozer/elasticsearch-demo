package com.backend.elastic.product.service;

import com.backend.elastic.config.model.ConfigRuleEntity;
import com.backend.elastic.config.repository.ConfigRuleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandSuggestionServiceImpl implements BrandSuggestionService {

    private static final String BRAND_CONFIG_NAME = "brand_suggestions";

    private final ConfigRuleRepository configRuleRepository;

    public BrandSuggestionServiceImpl(ConfigRuleRepository configRuleRepository) {
        this.configRuleRepository = configRuleRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getBrandsByCategory(String category) {
        Optional<ConfigRuleEntity> brandConfigRule = configRuleRepository.findByName(BRAND_CONFIG_NAME);

        return brandConfigRule
                .map(ConfigRuleEntity::getBody)
                .filter(body -> body != null)
                .map(body -> (Map<String, Object>) body.get("categoryToBrands"))
                .map(categoryMap -> (List<String>) categoryMap.get(category))
                .orElse(Collections.emptyList());
    }
}
