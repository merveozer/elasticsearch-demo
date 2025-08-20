package com.backend.elastic.config.controller;

import com.backend.elastic.config.dto.ConfigRuleRequestDto;
import com.backend.elastic.config.dto.ConfigRuleResponseDto;
import com.backend.elastic.config.service.ConfigRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/configs")
public class ConfigRuleController {

    private final ConfigRuleService configRuleService;

    public ConfigRuleController(ConfigRuleService configRuleService) {
        this.configRuleService = configRuleService;
    }

    @PostMapping
    public ResponseEntity<ConfigRuleResponseDto> createConfig(@RequestBody ConfigRuleRequestDto configRuleRequestDto) {
        configRuleService.createConfig(configRuleRequestDto.name(), configRuleRequestDto.body());
        ConfigRuleResponseDto configRuleResponseDto = new ConfigRuleResponseDto(configRuleRequestDto.name(), "Config created");
        return ResponseEntity.status(HttpStatus.CREATED).body(configRuleResponseDto);
    }

    @GetMapping
    public ResponseEntity<ConfigRuleResponseDto> getConfigByName(String configName) {
        ConfigRuleResponseDto configRuleResponseDto = new ConfigRuleResponseDto(configName, "Config found.");
        return ResponseEntity.ok(configRuleResponseDto);
    }
}
