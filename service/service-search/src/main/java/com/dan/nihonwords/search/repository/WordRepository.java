package com.dan.nihonwords.search.repository;

import org.dan.nihonwords.model.search.WordEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mcd
 * @create 2024-08-31 20:59
 */
public interface WordRepository extends ElasticsearchRepository<WordEs, Long> {
    Page<WordEs> findByKeyword(String keyword, Pageable pageable); //Spring Data Elasticsearch 支持通过命名约定自动生成查询方法。通过在仓库接口中定义方法名，Spring Data Elasticsearch 可以自动解析并执行相应的查询。
}
