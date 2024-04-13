package org.dan.nihonwords.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.acl.service.AdminRoleService;
import org.dan.nihonwords.acl.service.AdminService;
import org.dan.nihonwords.acl.service.RoleService;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.acl.Admin;
import org.dan.nihonwords.acl.utils.MD5;
import org.dan.nihonwords.vo.acl.AdminQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author mcd
 * @create 2023-07-19 10:46
 */

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/acl/user/")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;


    @ApiOperation("获取用户的所有角色")
    @GetMapping("toAssign/{id}")
    public Result getRoles(@PathVariable Long id){
        //返回两个数据，一个是所有角色列表，一个是该用户分配的角色列表。
        Map<String, Object> map = roleService.getRoleByAdminId(id);
        return Result.ok(map);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("doAssign")
    public Result assignRoles(@RequestParam Long adminId,
                              @RequestParam Long[] roleId){
        roleService.saveAdminRole(adminId, roleId);
        return Result.ok(null);
    }

    @ApiOperation("获取后台用户分页列表(带搜索)")
    @GetMapping("{page}/{limit}")
    public Result getUserPage(
            @PathVariable Long page,
            @PathVariable Long limit,
            AdminQueryVo adminQueryVo){

        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> adminModel = adminService.selectPage(pageParam, adminQueryVo);

        return Result.ok(adminModel);

    }

    @ApiOperation("根据Id获取管理用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    @ApiOperation("新增管理用户")
    @PostMapping("save")
    public Result add(@RequestBody Admin admin){
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        adminService.save(admin);
        return Result.ok(null);
    }

    @ApiOperation("更新管理用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return Result.ok(null);
    }

    @ApiOperation("删除管理用户")
    @DeleteMapping("remove/{id}")
    public Result deleteById(@PathVariable Long id){
        adminService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchDel(@RequestBody List<Long> ids){
        adminService.removeByIds(ids);
        return Result.ok(null);
    }


}
