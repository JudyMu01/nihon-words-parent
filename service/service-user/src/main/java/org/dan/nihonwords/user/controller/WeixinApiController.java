package org.dan.nihonwords.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.common.constant.RedisConst;
import org.dan.nihonwords.common.exception.NWordsException;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.common.result.ResultCodeEnum;
import org.dan.nihonwords.common.utils.JwtHelper;
import org.dan.nihonwords.model.user.User;
import org.dan.nihonwords.user.service.UserService;
import org.dan.nihonwords.user.utils.ConstantPropertiesUtil;
import org.dan.nihonwords.user.utils.HttpClientUtils;
import org.dan.nihonwords.vo.user.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author mcd
 * @create 2024-04-17 14:42
 */
@RestController
@RequestMapping("/api/user/weixin")
public class WeixinApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    //用户微信授权登录
    @ApiOperation(value = "微信登录获取openid（小程序）")
    @GetMapping("/wxLogin/{code}")
    public Result loginWx(@PathVariable String code) {
        //1 得到微信返回的code临时票据（即参数中的code）
        //2 拿code+ 小程序id+ 小程序密钥 请求微信接口服务（使用HTTPClient工具，一个请求微信接口的服务类
        String wxOpenAppId = ConstantPropertiesUtil.WX_OPEN_APP_ID;
        String wxOpenAppSecret = ConstantPropertiesUtil.WX_OPEN_APP_SECRET;
        //get请求
        ///拼接请求地址+参数 ：地址?name=value&name1=value1
        StringBuffer url = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&js_code=%s")
                .append("&grant_type=authorization_code");
        String tokenUrl = String.format(url.toString(),
                wxOpenAppId,
                wxOpenAppSecret,
                code);
        ///HttpClient发送get请求
        String result = null;
        try {
            result = HttpClientUtils.get(tokenUrl);
        } catch (Exception e) {
            //如若报错，返回一个自己写的exception，并返回枚举类，内涵错误代码和信息，这里是FETCH_ACCESSTOKEN_FAILD( 218, "获取accessToken失败")
            throw new NWordsException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        //3 请求微信接口返回值 session_key 和 openid（后者是微信用户唯一标识
        JSONObject jsonObject = JSONObject.parseObject(result);
        String session_key = jsonObject.getString("session_key");
        String openid = jsonObject.getString("openid");

        //4 添加微信用户信息到数据库
        // 操作user表，首先判断openId是否在表里，即是否第一次微信授权登录
        User user = userService.getUserByOpenId(openid);
        if(user == null){//first time log in
            user = new User();
            user.setOpenId(openid);
            user.setNickName(openid);
            user.setPhotoUrl("");
            //user.setUserType(UserType.USER);
            user.setIsNew(0);
            userService.save(user);

        }

        //5 根据userId查询用户的别的信息
        // 比如提货点 user表里，和团长信息 leader表

        //6 使用JWT工具 生成token字符串，根据userId和userName
        String token = JwtHelper.createToken(user.getId(),user.getNickName());

        //7 获取当前登录用户信息，放到Redis里，设置有效时间
        UserLoginVo userLoginVo = userService.getUserLoginVo(user.getId());
        redisTemplate.opsForValue().set(RedisConst.USER_LOGIN_KEY_PREFIX+user.getId(),
                userLoginVo,
                RedisConst.USERKEY_TIMEOUT,
                TimeUnit.DAYS);

        //8 数据封装到map 返回。
        Map<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("token", token);
        //map.put();


        return Result.ok(map);
    }

    @PostMapping("/auth/updateUser")
    @ApiOperation(value = "更新用户昵称和头像")
    public Result updateUser(@RequestBody User user){
        //获取当前登录用户的id
        User user1 = userService.getById(AuthContextHolder.getUserId());
        //把昵称更新为微信用户
        user1.setNickName(user.getNickName().replaceAll("ue000-uefff", "*"));
        user1.setPhotoUrl(user.getPhotoUrl());
        userService.updateById(user1);
        return Result.ok(null);
    }
}
