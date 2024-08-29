package org.dan.nihonwords.word.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.word.Book;
import org.dan.nihonwords.model.word.Booktype;
import org.dan.nihonwords.vo.word.BooktypeQueryVo;
import org.dan.nihonwords.word.service.BookService;
import org.dan.nihonwords.word.service.BooktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 词书类型 前端控制器
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Api(tags="词书类型管理")
@RestController
@RequestMapping("/admin/product/attrGroup")
public class BooktypeController {

    @Autowired
    private BooktypeService booktypeService;
    /**
    getPageList(page, limit, searchObj) {
        return request({
                url: `${api_name}/${page}/${limit}`,
        method: 'get',
                params: searchObj
    })
     */
    @ApiOperation("获取词书类型分页")
    @GetMapping("/{page}/{limit}")
    public Result getBooktypePage(@PathVariable Long page,
                                  @PathVariable Long limit,
                                  BooktypeQueryVo booktypeQueryVo){
        Page<Booktype> pageParam = new Page<>(page, limit);
        IPage<Booktype> booktypeIPage = booktypeService.getpage(pageParam, booktypeQueryVo);

        return Result.ok(booktypeIPage);
    }
    /**
    getById(id) {
        return request({
                url: `${api_name}/get/${id}`,
        method: 'get'
    })
    */

    @ApiOperation(("根据id获取"))
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        Booktype booktype = booktypeService.getById(id);
        return Result.ok(booktype);
    }
    /**
    save(role) {
        return request({
                url: `${api_name}/save`,
        method: 'post',
                data: role
    })
    */
    @ApiOperation("添加词书类型")
    @PostMapping("/save")
    public Result saveBooktype(@RequestBody Booktype booktype){
        booktypeService.save(booktype);
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
    @ApiOperation("更新信息")
    @PutMapping("/update")
    public Result update(@RequestBody Booktype booktype){
        booktypeService.updateById(booktype);
        return Result.ok(null);
    }

    /**
     removeById(id) {
        return request({
                url: `${api_name}/remove/${id}`,
        method: 'delete'
    })
    */
    @ApiOperation("根据id删除")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        booktypeService.removeById(id);
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
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result deleteBatch(@RequestBody List<Long> idList){

        booktypeService.removeByIds(idList);
        return Result.ok(null);
    }
    /**

    findAllList() {
        return request({
                url: `${api_name}/findAllList`,
        method: 'get'
    })
    }
    */
    @ApiOperation("查询所有词书")
    @GetMapping("/findAllList")
    public Result getList(){

        List<Booktype> booktypes = booktypeService.getBaseMapper().selectList(new QueryWrapper<Booktype>().orderByAsc("CAST(id AS SIGNED)"));
        return Result.ok(booktypes);
    }

}

