package org.dan.nihonwords.common.auth;

import org.dan.nihonwords.common.constant.RedisConst;
import org.dan.nihonwords.common.utils.JwtHelper;
import org.dan.nihonwords.vo.user.UserLoginVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mcd
 * @create 2024-07-31 19:24
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    private RedisTemplate redisTemplate;

    public UserLoginInterceptor(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    /**
     * 在请求业务方法调用之前运行的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        this.getUserLoginVo(request); //从请求头获得信息
        return true;
    }

    private void getUserLoginVo(HttpServletRequest request) {
        //从请求头获取token
        String token = request.getHeader("token");
        //token不为空，获取userId
        if(!StringUtils.isEmpty(token)){
            Long userId = JwtHelper.getUserId(token);
            //根据userId到Redis获取用户信息
            UserLoginVo userLoginVo = (UserLoginVo)redisTemplate.opsForValue().get(RedisConst.USER_LOGIN_KEY_PREFIX + userId);

            //获取数据放入ThreadLocal
            if(userLoginVo!=null){
                AuthContextHolder.setUserId(userLoginVo.getUserId());
                AuthContextHolder.setUserLoginVo(userLoginVo);
            }
        }


    }

}
