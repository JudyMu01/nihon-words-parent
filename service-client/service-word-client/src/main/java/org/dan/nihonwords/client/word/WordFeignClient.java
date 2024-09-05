package org.dan.nihonwords.client.word;

import org.dan.nihonwords.model.word.Booktype;
import org.dan.nihonwords.vo.word.WordVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author mcd
 * @create 2024-08-02 21:05
 */
@FeignClient(value = "service-word")
public interface WordFeignClient {

    @GetMapping("/api/product/inner/findAllCategoryList")
    public List<Booktype> findAllCategoryList();
    @GetMapping("/api/product/inner/getCategory/{categoryId}")
    public Booktype getCategory(@PathVariable("categoryId") Long categoryId);
    @GetMapping("api/product/inner/getWordList/{wordbookId}")
    public List<Long> getWordList(@PathVariable Long wordbookId);

    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public WordVo getSkuInfo(@PathVariable("skuId") Long skuId);


    Map<Long, List<String>> findMeanings(List<Long> wordIdList);
}
