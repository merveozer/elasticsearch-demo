package com.backend.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories(basePackages = {"com.backend.elastic.product.repository.mongo", "com.backend.elastic.config" +
		".repository"})
@EnableElasticsearchRepositories(basePackages = "com.backend.elastic.product.repository.elastic")
@SpringBootApplication
public class ElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticApplication.class, args);
	}

}
