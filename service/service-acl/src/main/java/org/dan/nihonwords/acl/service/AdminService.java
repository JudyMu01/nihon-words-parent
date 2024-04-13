package org.dan.nihonwords.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.model.acl.Admin;
import org.dan.nihonwords.vo.acl.AdminQueryVo;

/**
 * @author mcd
 * @create 2023-07-19 16:23
 */
public interface AdminService extends IService<Admin> {


    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo);
}
