package org.dan.nihonwords.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.acl.mapper.AdminRoleMapper;
import org.dan.nihonwords.acl.service.AdminRoleService;
import org.dan.nihonwords.model.acl.AdminRole;
import org.springframework.stereotype.Service;

/**
 * @author mcd
 * @create 2023-07-21 16:13
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole>
        implements AdminRoleService {
}
