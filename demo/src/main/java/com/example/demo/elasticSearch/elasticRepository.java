package com.example.demo.elasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface elasticRepository extends ElasticsearchRepository<my_entity, String> {
    
}
