package com.dan.nihonwords.home.service.impl;

import com.dan.nihonwords.home.service.HomeService;
import org.dan.nihonwords.client.word.WordFeignClient;
import org.dan.nihonwords.model.word.Booktype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mcd
 * @create 2024-08-02 17:31
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private WordFeignClient wordFeignClient;
    //首页显示数据接口
    @Override
    public Map<String, Object> homeData(Long userId) {
        //封装获取的数据到map集合
        Map<String, Object> result = new HashMap<>();
        //根据userId获取当前登录语句地址信息
        //远程调用servive-user模块接口


        //获取所有词书分类
        //远程调用service-word模块接口
        List<Booktype> booktypeList = wordFeignClient.findAllCategoryList();
        result.put("booktypeList", booktypeList);


        return result;
    }
}
