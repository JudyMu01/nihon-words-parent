package org.dan.nihonwords.user.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 这个类可以获取配置文件中的小程序测试账号信息
 * @author mcd
 * @create 2024-04-13 21:42
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean { //初始化bean的时候就可以注入

    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
    }
}
