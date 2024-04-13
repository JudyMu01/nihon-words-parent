package org.dan.nihonwords.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.model.acl.Permission;

import java.util.List;

/**
 * @author mcd
 * @create 2023-07-24 17:54
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> getList();

    void deleteById(Long id);

    List<Permission> getPermissionByRoleId(Long roleId);

    void saveRolePermission(Long roleId, Long[] permissionId);
}
