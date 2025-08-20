package com.backend.elastic.config.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configs")
public class ConfigRuleEntity {
    @Id
    private String id;
    private String name;
    private org.bson.Document body;

    public ConfigRuleEntity() {
    }
    public ConfigRuleEntity(String name, org.bson.Document body) {
        this.name = name;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public org.bson.Document getBody() {
        return body;
    }

    public void setBody(org.bson.Document body) {
        this.body = body;
    }
}
