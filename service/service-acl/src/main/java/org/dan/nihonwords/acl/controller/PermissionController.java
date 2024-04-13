package org.dan.nihonwords.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.dan.nihonwords.acl.service.PermissionService;
import org.dan.nihonwords.acl.service.RolePermissionService;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.acl.Permission;
import org.dan.nihonwords.model.acl.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcd
 * @create 2023-07-24 16:18
 */

@Api(tags="权限管理接口")
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 获取全部权限列表，以树的形式
     * @return
     */
    @ApiOperation("获取权限列表")
    @GetMapping
    public Result getList(){
        List<Permission> list = permissionService.getList();
        return Result.ok(list);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @ApiOperation("删除权限（递归）")
    @DeleteMapping("/remove/{id}")
    public Result deleteById(@PathVariable Long id){
        permissionService.deleteById(id);
        return Result.ok(null);

    }

    /**
     * 保存一个权限项
     * @param permission
     * @return
     */
    @ApiOperation("保存权限")
    @PostMapping("/save")
    public Result save(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);

    }

    /**
     * 更新一个权限项
     * @param permission
     * @return
     */
    @ApiOperation("更新权限")
    @PutMapping("/update")
    public Result update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);

    }

    /**
     * 查看某个角色的权限列表
     * @param roleId
     * @return tree list
     */
    @ApiOperation("查看某个角色的权限列表")
    @GetMapping("/toAssign/{roleId}")
    public Result getPermissionById(@PathVariable Long roleId){
        List<Permission> list = permissionService.getPermissionByRoleId(roleId);
        return Result.ok(list);

    }
    /*
    给某个角色授权

    doAssign(roleId, permissionId) {
        return request({
                url: `${api_name}/doAssign`,
        method: "post",
                params: {roleId, permissionId}
    })
    }
    */
    @ApiOperation("给某个角色授权")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long roleId, @RequestParam Long[] permissionId){

        permissionService.saveRolePermission(roleId, permissionId);

        return Result.ok(null);

    }


}
