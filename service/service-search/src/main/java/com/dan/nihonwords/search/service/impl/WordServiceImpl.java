package com.dan.nihonwords.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.dan.nihonwords.search.repository.WordRepository;
import com.dan.nihonwords.search.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.dan.nihonwords.client.word.WordFeignClient;
import org.dan.nihonwords.model.search.WordEs;
import org.dan.nihonwords.model.word.WordMeaning;
import org.dan.nihonwords.vo.word.WordVo;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mcd
 * @create 2024-08-29 21:03
 */
@Slf4j
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordFeignClient wordFeignClient;

    @Autowired
    private WordRepository wordEsRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 发布单词列表
     * @param skuId
     */
    @Override
    public void publishWord(Long skuId) {
        log.info("publishWord："+skuId);
        WordEs wordEs = new WordEs();

        //查询单词信息
        WordVo wordVo = wordFeignClient.getSkuInfo(skuId);
        if(null == wordVo) return;

        wordEs.setId(wordVo.getId());
        wordEs.setKeyword(wordVo.getWord());
        wordEs.setImgUrl(wordVo.getImgUrl());
        wordEs.setTitle(wordVo.getWord());
        wordEs.setAccent(wordVo.getAccent());
        wordEs.setKana(wordVo.getKana());
        List<String> meanings = wordVo.getWordMeaningList().stream()
                .map(WordMeaning::getTranslate)
                .collect(Collectors.toList());
        wordEs.setMeaningList(meanings);

        WordEs save = wordEsRepository.save(wordEs);
        log.info("upperSku："+ JSON.toJSONString(save));
    }

    /**
     * 撤回单词列表
     * @param skuId
     */
    @Override
    public void hideWord(Long skuId) {
        this.wordEsRepository.deleteById(skuId);
    }
}
