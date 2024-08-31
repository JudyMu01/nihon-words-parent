package com.dan.nihonwords.search.api;

import com.dan.nihonwords.search.service.WordService;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mcd
 * @create 2024-08-29 20:56
 */
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {

    @Autowired
    private WordService wordService;

    @ApiOperation(value = "发布单词")
    @GetMapping("inner/upperSku/{skuId}")
    public Result publishWord(@PathVariable("skuId") Long skuId) {
        wordService.publishWord(skuId);
        return Result.ok(null);
    }

    @ApiOperation(value = "撤回单词")
    @GetMapping("inner/lowerSku/{skuId}")
    public Result hideWord(@PathVariable("skuId") Long skuId) {
        wordService.hideWord(skuId);
        return Result.ok(null);
    }
}
