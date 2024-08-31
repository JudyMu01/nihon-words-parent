package com.dan.nihonwords.search.service;

import org.springframework.stereotype.Service;

/**
 * @author mcd
 * @create 2024-08-29 21:01
 */
@Service
public interface WordService {
    void publishWord(Long skuId);

    void hideWord(Long skuId);
}
