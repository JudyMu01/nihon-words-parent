package org.dan.nihonwords.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.model.acl.Role;
import org.dan.nihonwords.vo.acl.RoleQueryVo;

import java.util.Map;

/**
 * @author mcd
 * @create 2023-07-18 16:47
 */
public interface RoleService extends IService<Role> {


    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Map<String, Object> getRoleByAdminId(Long id);

    void saveAdminRole(Long adminId, Long[] roleIds);
}
