package com.backend.elastic.config.repository;

import com.backend.elastic.config.model.ConfigRuleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigRuleRepository extends MongoRepository<ConfigRuleEntity, String> {
    Optional<ConfigRuleEntity> findByName(String name);
}
