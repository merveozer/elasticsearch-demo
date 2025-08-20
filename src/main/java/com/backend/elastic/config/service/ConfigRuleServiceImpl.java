package com.backend.elastic.config.service;

import com.backend.elastic.config.model.ConfigRuleEntity;
import com.backend.elastic.config.repository.ConfigRuleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ConfigRuleServiceImpl implements ConfigRuleService {

    private final ConfigRuleRepository repository;

    public ConfigRuleServiceImpl(ConfigRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> getConfigBodyByName(String configName) {
        return repository.findByName(configName)
                .map(ConfigRuleEntity::getBody)
                .map(doc -> (Map<String, Object>) doc)
                .orElse(Collections.emptyMap());
    }

    @Override
    public Map<String, Object> createConfig(String name, Map<String, Object> body) {
        org.bson.Document document = new org.bson.Document(body);
        return repository.save(new ConfigRuleEntity(name, document)).getBody();
    }
}
