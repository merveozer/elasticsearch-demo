package com.backend.elastic.product.repository.elastic;

import com.backend.elastic.product.model.mongo.ProductMongoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductMongoEntity, String> {
    List<ProductMongoEntity> findByNameContaining(String name);
}
