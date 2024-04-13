package org.dan.nihonwords.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dan.nihonwords.model.acl.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author mcd
 * @create 2023-07-24 17:55
 */

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
}
