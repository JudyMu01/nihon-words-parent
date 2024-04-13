package org.dan.nihonwords.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.acl.mapper.PermissionMapper;
import org.dan.nihonwords.acl.service.PermissionService;
import org.dan.nihonwords.acl.service.RolePermissionService;
import org.dan.nihonwords.acl.service.RoleService;
import org.dan.nihonwords.acl.utils.PermissionHelper;
import org.dan.nihonwords.model.acl.Permission;
import org.dan.nihonwords.model.acl.Role;
import org.dan.nihonwords.model.acl.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcd
 * @create 2023-07-24 17:54
 */

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> getList() {
        //查询所有
        List<Permission> allList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));
        //创建需要的树形结构
        List<Permission> result = PermissionHelper.buildPermission(allList);

        return result;
    }

    @Override
    public void deleteById(Long id) {

        //创建id列表
        List<Long> idList = new ArrayList<>();

        //递归找到所有要删除的子权限
        this.getAllPermissionId(id, idList);
        idList.add(id);

        //删除这些权限
        baseMapper.deleteBatchIds(idList);

    }

    @Override
    public List<Permission> getPermissionByRoleId(Long roleId) {
        //找到roleId对应的permission ids
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rpList = rolePermissionService.list(wrapper);
        List<Long> permissionIds = new ArrayList<>();
        for(RolePermission rp : rpList){
            permissionIds.add(rp.getPermissionId());
        }

        //根据permission ids获得permission，并制作树形结构
        List<Permission> pList = baseMapper.selectBatchIds(permissionIds);
        List<Permission> answer = PermissionHelper.buildPermission(pList);

        return answer;

    }

    @Override
    public void saveRolePermission(Long roleId, Long[] permissionId) {
        //删除该role所有permission
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(wrapper);

        //保存permission
        List<RolePermission> rpList = new ArrayList<>();
        for(Long id:permissionId){
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(id);
            rpList.add(rp);
        }
        rolePermissionService.saveBatch(rpList);

    }

    private void getAllPermissionId(Long id, List<Long> idList) {
        if(id==null){
            return;
        }

        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid, id);
        List<Permission> list = baseMapper.selectList(wrapper);
        for(Permission permission:list){
            Long childId = permission.getId();
            idList.add(childId);
            getAllPermissionId(childId,idList);
        }
    }
}
