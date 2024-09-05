package com.dan.nihonwords.search.service;

import org.dan.nihonwords.model.search.WordEs;
import org.dan.nihonwords.vo.search.WordEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author mcd
 * @create 2024-08-29 21:01
 */
@Service
public interface WordService {
    void publishWord(Long skuId);

    void hideWord(Long skuId);

    Page<WordEs> search(Pageable pageable, WordEsQueryVo searchParamVo);
}
