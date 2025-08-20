package com.backend.elastic.config.service;

import java.util.Map;

public interface ConfigRuleService {
    public Map<String, Object> getConfigBodyByName(String configName);
    public Map<String, Object> createConfig(String name, Map<String, Object> body);
}
