package org.dan.nihonwords.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.model.user.User;
import org.dan.nihonwords.user.mapper.UserMapper;
import org.dan.nihonwords.user.service.UserService;
import org.dan.nihonwords.vo.user.UserLoginVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mcd
 * @create 2024-04-27 15:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserByOpenId(String openid) {
        User user = baseMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getOpenId, openid)
        );
        return user;
    }

    @Override
    public UserLoginVo getUserLoginVo(Long id) {
        User user = baseMapper.selectById(id);
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUserId(id);
        userLoginVo.setNickName(user.getNickName());
        userLoginVo.setPhotoUrl(user.getPhotoUrl());
        userLoginVo.setIsNew(user.getIsNew());
        userLoginVo.setOpenId(user.getOpenId());
//        userLoginVo.setLeaderId();
//        userLoginVo.setWareId();

        return userLoginVo;
    }
}
