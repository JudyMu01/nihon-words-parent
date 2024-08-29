package com.dan.nihonwords.home.controller;

import com.dan.nihonwords.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mcd
 * @create 2024-08-02 17:30
 */

@Api(tags = "首页接口")
@RestController
@RequestMapping("api/home")
public class HomeApiController {

    @Autowired
    private HomeService homeService;

    @ApiOperation("首页数据显示接口")
    @GetMapping("index")
    public Result index(HttpServletRequest request){
        Long userId = AuthContextHolder.getUserId();//不用request也可以得到userId，因为拦截器创建了线程变量
        Map<String, Object> map = homeService.homeData(userId);


        return Result.ok(map);
    }
}
