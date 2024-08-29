package org.dan.nihonwords.word.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.word.Word;
import org.dan.nihonwords.vo.word.WordQueryVo;
import org.dan.nihonwords.vo.word.WordVo;
import org.dan.nihonwords.word.service.MeaningExampleService;
import org.dan.nihonwords.word.service.WordInBookService;
import org.dan.nihonwords.word.service.WordMeaningService;
import org.dan.nihonwords.word.service.WordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 单词信息 前端控制器
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@RestController
@Api(tags="单词管理")
@RequestMapping("/admin/product/skuInfo")
public class WordController {

    @Autowired
    private WordService wordService;

    /**
     *
     * @param page
     * @param limit
     * @param wordQueryVo
     * @return
     */
    @ApiOperation("分页获取单词列表")
    @GetMapping("/{page}/{limit}")
    public Result getPageWord(@PathVariable Long page,
                              @PathVariable Long limit,
                              WordQueryVo wordQueryVo){

        Page<Word> pageParam = new Page<>(page, limit);
        IPage<Word> wordModel = wordService.selectPage(pageParam, wordQueryVo);

        return Result.ok(wordModel);
    }


    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询")
    @GetMapping("/get/{id}")
    public Result<WordVo> getWordById(@PathVariable Long id){
        WordVo wordVo = wordService.getWordVoById(id);
        return Result.ok(wordVo);
    }

    /**
     *
     * @param wordVo
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation("新增单词")
    @PostMapping("/save")
    public Result save(@RequestBody WordVo wordVo){

        wordService.saveWordInfo(wordVo);

        return Result.ok(null);
    }
    /**
    updateById(role) {
        return request({
                url: `${api_name}/update`,
        method: 'put',
                data: role
    })
     */
    @ApiOperation("修改单词信息")
    @PutMapping("/update")
    public Result update(@RequestBody WordVo wordVo){

        wordService.updateWord(wordVo);
        return Result.ok(null);
    }
    /**
    removeById(id) {
        return request({
                url: `${api_name}/remove/${id}`,
        method: 'delete'
    })
     */
    @ApiOperation("删除单词")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Long id){

        wordService.removeById(id);
        return Result.ok(null);
    }
    /**
    removeRows(idList) {
        return request({
                url: `${api_name}/batchRemove`,
        method: 'delete',
                data: idList
    })
    */

    @ApiOperation("批量删除单词")
    @DeleteMapping("/batchRemove")
    public Result deleteBatch(@RequestBody List<Long> ids){
        wordService.removeByIds(ids);
        return Result.ok(null);
    }
    /**
    //商品上架
    publish(id, status) {
        return request({
                url: `${api_name}/publish/${id}/${status}`,
        method: 'get'
    })
     */

    @ApiOperation("商品上架")
    @GetMapping("publish/{id}/{status}")
    public Result publish(@PathVariable("id") Long id,
                          @PathVariable("status") Boolean status) {
        wordService.publish(id, status);
        return Result.ok(null);
    }

    /**
    //商品审核
    check(id, status) {
        return request({
                url: `${api_name}/check/${id}/${status}`,
        method: 'get'
    })
    */
    @ApiOperation("商品审核")
    @GetMapping("/check/{id}/{status}")
    public Result check(@PathVariable("id") Long id,
                        @PathVariable("status") Boolean status){
        wordService.check(id,status);
        return Result.ok(null);
    }

}

