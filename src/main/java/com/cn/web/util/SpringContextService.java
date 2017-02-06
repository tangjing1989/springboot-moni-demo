package com.cn.web.util;

import com.cn.web.util.config.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Describe:应用启动时候的初始化，和全局参数的内存化
 * User:tangjing
 * Date:17/1/25
 * Time:上午10:15
 */
@EnableAutoConfiguration
public class SpringContextService {

    @Autowired
    IConfigService iConfigService;

    //应用服务器类型
    public static String SYS_SERVER_TYPE="";
    public static String IS_KAPTCHA="";


    public void init() {

        //获取应用服务器类型
        this.SYS_SERVER_TYPE=iConfigService.getMiddleware();
        this.IS_KAPTCHA=iConfigService.getIskaptcha();
    }
}
