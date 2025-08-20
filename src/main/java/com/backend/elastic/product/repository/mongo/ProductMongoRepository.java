package com.backend.elastic.product.repository.mongo;

import com.backend.elastic.product.model.mongo.ProductMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductMongoEntity, String> {
}
