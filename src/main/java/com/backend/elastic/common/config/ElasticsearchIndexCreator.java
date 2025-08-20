package com.backend.elastic.common.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
public class ElasticsearchIndexCreator implements ApplicationRunner {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    public ElasticsearchIndexCreator(ElasticsearchOperations elasticsearchOperations, ObjectMapper objectMapper) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        String indexName = "products";
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);

        if (elasticsearchOperations.indexOps(indexCoordinates).exists()) {
            System.out.println("Elasticsearch index '" + indexName + "' already exists. Skipping creation.");
            return;
        }

        // 1. JSON dosyasını String olarak oku
        ClassPathResource resource = new ClassPathResource("es-settings-and-mappings.json");
        String settingsAndMappingsJson = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

        // 2. JSON String'i Map'e dönüştür
        @SuppressWarnings("unchecked")
        Map<String, Object> allProperties = objectMapper.readValue(settingsAndMappingsJson, Map.class);

        // 3. Settings ve Mappings kısımlarını ayır
        @SuppressWarnings("unchecked")
        Map<String, Object> settingsMap = (Map<String, Object>) allProperties.get("settings");

        @SuppressWarnings("unchecked")
        Map<String, Object> mappingsMap = (Map<String, Object>) allProperties.get("mappings");

        // 4. Map'leri Document objelerine dönüştür
        Document settingsDoc = Document.from(settingsMap);
        Document mappingsDoc = Document.from(mappingsMap);

        // 5. İndeksi ayarlar ve eşlemelerle oluştur
        boolean created = elasticsearchOperations.indexOps(indexCoordinates).create(settingsDoc, mappingsDoc);

        if (created) {
            System.out.println("Elasticsearch index '" + indexName + "' successfully created.");
        } else {
            System.err.println("Failed to create Elasticsearch index '" + indexName + "'.");
        }
    }
}