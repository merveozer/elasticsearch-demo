package com.backend.elastic.product.service;

import com.backend.elastic.product.dto.ProductRequestDto;
import com.backend.elastic.product.dto.ProductResponseDto;
import com.backend.elastic.product.dto.SearchResponseDto;
import com.backend.elastic.product.mapper.ProductMapper;
import com.backend.elastic.product.model.elastic.ProductElasticEntity;
import com.backend.elastic.product.model.mongo.ProductMongoEntity;
import com.backend.elastic.product.repository.elastic.ProductElasticRepository;
import com.backend.elastic.product.repository.mongo.ProductMongoRepository;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMongoRepository mongoRepository;
    private final ProductElasticRepository elasticRepository;
    private final ProductMapper mapper;
    private final ElasticsearchOperations elasticsearchOperations;
    private final BrandSuggestionService brandSuggestionService;

    public ProductServiceImpl(ProductMongoRepository mongoRepository, ProductElasticRepository elasticRepository,
                              ProductMapper mapper, ElasticsearchOperations elasticsearchOperations,
                              BrandSuggestionService brandSuggestionService) {
        this.mongoRepository = mongoRepository;
        this.elasticRepository = elasticRepository;
        this.mapper = mapper;
        this.elasticsearchOperations = elasticsearchOperations;
        this.brandSuggestionService = brandSuggestionService;
    }


    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        ProductMongoEntity productEntity = mapper.toEntity(productRequestDto);
        ProductMongoEntity savedMongoEntity = mongoRepository.save(productEntity);
        elasticRepository.save(productEntity);
        return new ProductResponseDto(savedMongoEntity, "Product created successfully");
    }

    @Override
    public SearchResponseDto getProductWithNameAndBrandSuggestions(String name) {
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .match(m -> m
                                .field("name")
                                .query(name)
                        )
                )
                .build();
        SearchHits<ProductElasticEntity> searchHits = elasticsearchOperations.search(nativeQuery,
                ProductElasticEntity.class);

        List<ProductResponseDto> productDtoList = searchHits.getSearchHits().stream()
                .map(hit -> new ProductResponseDto(mapper.toDto(hit.getContent()), "Product found"))
                .collect(Collectors.toList());

        List<String> suggestedBrands = Collections.emptyList();
        if (!productDtoList.isEmpty()) {
            String category = searchHits.getSearchHits().get(0).getContent().getCategory();
            suggestedBrands = brandSuggestionService.getBrandsByCategory(category);
        }

        return new SearchResponseDto(productDtoList, suggestedBrands);
    }
}
