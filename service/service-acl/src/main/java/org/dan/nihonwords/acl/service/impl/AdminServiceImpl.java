package org.dan.nihonwords.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.acl.mapper.AdminMapper;
import org.dan.nihonwords.acl.service.AdminService;
import org.dan.nihonwords.model.acl.Admin;
import org.dan.nihonwords.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author mcd
 * @create 2023-07-19 17:20
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {

        String username = adminQueryVo.getUsername();
        String name = adminQueryVo.getName();
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(username)){
            wrapper.eq(Admin::getUsername, username);
        }
        if(!StringUtils.isEmpty(name)){
            wrapper.like(Admin::getName, name);
        }

        IPage<Admin> adminModel = baseMapper.selectPage(pageParam, wrapper);

        return adminModel;
    }
}
