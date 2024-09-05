package com.dan.nihonwords.search.api;

import com.dan.nihonwords.search.service.WordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.search.WordEs;
import org.dan.nihonwords.vo.search.WordEsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @ApiOperation(value = "搜索单词")
    @GetMapping("{page}/{limit}")
    public Result list(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "searchParamVo", value = "查询对象", required = true)
            WordEsQueryVo wordEsQueryVo) {

        Pageable pageable = PageRequest.of(page-1, limit);
        Page<WordEs> pageModel =  wordService.search(pageable, wordEsQueryVo);
        return Result.ok(pageModel);
    }
}
