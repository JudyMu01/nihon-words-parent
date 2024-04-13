package org.dan.nihonwords.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.acl.service.RoleService;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.acl.Role;
import org.dan.nihonwords.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mcd
 * @create 2023-07-18 16:45
 */

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role/")
@CrossOrigin
public class RoleController {

    //注入Service
    @Autowired
    private RoleService roleService;

    /**
     * 1.角色列表，条件分页查询
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param roleQueryVo 查询对象
     * @return
     */
    @ApiOperation("获取角色列表分页")
    @GetMapping("{page}/{limit}")
    public Result getRole(
            @PathVariable Long page,
            @PathVariable Long limit,
            RoleQueryVo roleQueryVo
    ){

        //创建Page对象，传递page当前页和limit每页显示记录数
        Page<Role> pageParam = new Page<>(page, limit);
        //调用Service方法实现分页查询，返回分页对象
        IPage<Role> roleModel = roleService.selectPage(pageParam, roleQueryVo);
        return Result.ok(roleModel);
    }

    /**
     * 2.添加新角色
     * @param role 角色对象
     * @return
     */
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result addRole(@RequestBody Role role){
        roleService.save(role);
        return Result.ok(null);
    }


    @ApiOperation("根据id获取角色")
    @GetMapping("get/{id}")
    public Result getRoleById(@PathVariable Long id){
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    @ApiOperation("根据id修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody Role role){
        roleService.updateById(role);
        return Result.ok(null);
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result deleteById(@PathVariable Long id){
        roleService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("根据id删除多个角色")
    @DeleteMapping("batchRemove")
    public Result deleteByIds(@RequestBody List<Long> ids){//requestbody注释很重要，会封装json格式从而传递正确内容。
        roleService.removeByIds(ids);
        return Result.ok(null);
    }

}
