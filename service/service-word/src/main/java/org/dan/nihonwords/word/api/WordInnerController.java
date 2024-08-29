package org.dan.nihonwords.word.api;

import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.model.word.Booktype;
import org.dan.nihonwords.word.service.BooktypeService;
import org.dan.nihonwords.word.service.WordInBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-03 17:41
 */
@RestController
@RequestMapping("/api/product")
public class WordInnerController {

    @Autowired
    private BooktypeService booktypeService;

    @Autowired
    private WordInBookService wordInBookService;

    //根据词书类型id获取类型信息
    @ApiOperation(value = "获取分类信息")
    @GetMapping("inner/getCategory/{categoryId}")
    public Booktype getPage(@PathVariable Long id){
        Booktype booktype = booktypeService.getById(id);
        return booktype;
    }
//    //根据单词id获取单词信息

    //获取全部词书类型
    @ApiOperation(value = "获取全部词书类型信息")
    @GetMapping("inner/findAllCategoryList")
    public List<Booktype> findAllCategoryList(){
        List<Booktype> list = booktypeService.list();
        return list;
    }
    //根据词书id获得单词id列表
    @ApiOperation(value = "根据词书id获得单词id列表")
    @GetMapping("inner/getWordList/{wordbookId}")
    public List<Long> getWordList(@PathVariable Long wordbookId){
        List<Long> wordIdList = wordInBookService.getWordIdListByBookId(wordbookId);

        return wordIdList;
    }
}
