package org.dan.nihonwords.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.acl.mapper.RoleMapper;
import org.dan.nihonwords.acl.service.AdminRoleService;
import org.dan.nihonwords.acl.service.RoleService;
import org.dan.nihonwords.model.acl.AdminRole;
import org.dan.nihonwords.model.acl.Role;
import org.dan.nihonwords.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mcd
 * @create 2023-07-18 16:48
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    private AdminRoleService adminRoleService;
    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {

        //获取条件值
        String roleName = roleQueryVo.getRoleName();

        //将查询条件判断不为空后包装
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        //模糊查询 roleName like？
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(Role::getRoleName,roleName);
        }

        //调用方法实现分页查询。这里的baseMapper是mp帮忙将mapper类注入的名字。
        IPage<Role> roleModel = baseMapper.selectPage(pageParam, wrapper);

        return roleModel;
    }

    /**
     * 查询所有角色，以及该用户已分配角色
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getRoleByAdminId(Long id) {

        //查询所有角色
        List<Role> allRoleList = baseMapper.selectList(null);

        //根据用户id查询用户分配角色列表
        //这里没有使用关联查询！很神奇。
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, id);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);//

        List<Long> roleIdList = new ArrayList<>();//
        for(AdminRole item : adminRoleList){
            roleIdList.add(item.getRoleId());
        }

        List<Role> assignRoleList = new ArrayList<>();
        for(Role role:allRoleList){
            if(roleIdList.contains(role.getId())){
                assignRoleList.add(role);
            }
        }


        //封装，返回
        Map<String, Object> map = new HashMap<>();
        map.put("allRolesList", allRoleList);
        map.put("assignRoles",assignRoleList);
        return map;
    }

    /**
     * 为用户分配角色
     * @param adminId
     * @param roleIds
     */
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //首先删除用户原先分配的角色
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(wrapper);

        //然后存新分配的角色
        List<AdminRole> arList = new ArrayList<>();
        for(Long id : roleIds){
            AdminRole ar = new AdminRole();
            ar.setAdminId(adminId);
            ar.setRoleId(id);
            arList.add(ar);
        }
        adminRoleService.saveBatch(arList);
    }
}
