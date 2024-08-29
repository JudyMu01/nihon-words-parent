package org.dan.nihonwords.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.model.user.User;
import org.dan.nihonwords.vo.user.UserLoginVo;

/**
 * @author mcd
 * @create 2024-04-19 10:29
 */

public interface UserService extends IService<User> {
    /**
     * 根据微信openid获取用户信息
     * @param openid
     * @return
     */
    User getUserByOpenId(String openid);

    /**
     * 获取当前登录用户信息
     * @param id
     * @return
     */
    UserLoginVo getUserLoginVo(Long id);
}
