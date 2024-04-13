package org.dan.nihonwords.word.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.acl.Admin;
import org.dan.nihonwords.model.acl.Permission;
import org.dan.nihonwords.model.word.Book;
import org.dan.nihonwords.vo.word.BookQueryVo;
import org.dan.nihonwords.word.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 词书 前端控制器
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@RestController
@Api(tags="词书管理")
@RequestMapping("/admin/product/category")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;


    @ApiOperation("分页查询所有词书")
    @GetMapping("/{page}/{limit}")
    public Result getPage(@PathVariable Long page,
                          @PathVariable Long limit,
                          BookQueryVo bookQueryVo){
        Page<Book> pageParam = new Page<>(page, limit);
        IPage<Book> bookModel = bookService.selectPage(pageParam, bookQueryVo);

        return Result.ok(bookModel);

    }


     @ApiOperation("通过id查询词书")
     @GetMapping("/get/{id}")
     public Result getBook(@PathVariable Long id){
         Book book = bookService.getById(id);
         return Result.ok(book);
     }


     @ApiOperation("添加词书")
     @PostMapping("/save")
     public Result saveBook(@RequestBody Book book){

         bookService.save(book);
         return Result.ok(null);
     }


     @ApiOperation("更新词书")
     @PutMapping("/update")
     public Result updateBook(@RequestBody Book book){

         bookService.updateById(book);
         return Result.ok(null);
     }

     @ApiOperation("删除词书")
     @DeleteMapping("/remove/{id}")
     public Result removeBook(@PathVariable Long id){

         bookService.removeById(id);
         return Result.ok(null);
     }


     @ApiOperation("批量删除词书")
     @DeleteMapping("/batchRemove")
     public Result removeBooks(@RequestBody List<Long> idList){

         bookService.removeByIds(idList);
         return Result.ok(null);
     }


     @ApiOperation("查询所有词书")
     @GetMapping("/findAllList")
     public Result getList(){

         List<Book> books = bookService.getBaseMapper().selectList(new QueryWrapper<Book>().orderByAsc("CAST(id AS SIGNED)"));
         return Result.ok(books);
     }

}

