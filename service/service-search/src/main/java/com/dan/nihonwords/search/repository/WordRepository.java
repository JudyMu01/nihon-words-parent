package com.dan.nihonwords.search.repository;

import org.dan.nihonwords.model.search.WordEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mcd
 * @create 2024-08-31 20:59
 */
public interface WordRepository extends ElasticsearchRepository<WordEs, Long> {
}
