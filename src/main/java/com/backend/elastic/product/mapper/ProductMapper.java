package com.backend.elastic.product.mapper;

import com.backend.elastic.product.dto.ProductRequestDto;
import com.backend.elastic.product.model.elastic.ProductElasticEntity;
import com.backend.elastic.product.model.mongo.ProductMongoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMongoEntity toEntity(ProductRequestDto dto);

    ProductRequestDto toDto(ProductElasticEntity entity);
}
