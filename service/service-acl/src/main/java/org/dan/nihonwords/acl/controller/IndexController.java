package org.dan.nihonwords.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcd
 * @create 2023-07-17 13:36
 */

@RestController
@Api(tags = "登录接口")
@RequestMapping("/admin/acl/index/")
@CrossOrigin//跨域
public class IndexController {

    /**
     * 请求登录的login
     * @return Result
     */

    @PostMapping ("login")
    @ApiOperation("登录")
    public Result login(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);

    }

    @GetMapping("info")
    @ApiOperation("获取用户信息")
    public Result getUserInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","dan");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("logout")
    @ApiOperation("登出")
    public Result logout(){
        return Result.ok(null);
    }
}
