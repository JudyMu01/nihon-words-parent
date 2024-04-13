package org.dan.nihonwords.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.acl.mapper.RolePermissionMapper;
import org.dan.nihonwords.acl.service.RolePermissionService;
import org.dan.nihonwords.model.acl.RolePermission;
import org.springframework.stereotype.Service;

/**
 * @author mcd
 * @create 2023-07-25 14:19
 */

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
