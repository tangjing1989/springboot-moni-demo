package com.cn.config;

import com.cn.web.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Describe:
 * User:tangjing
 * Date:17/2/3
 * Time:上午10:34
 */
@EnableWebMvc
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        String[] excludePaths = new String[3];
        excludePaths[0] = "/welcome.htm";
        excludePaths[1] = "/login.htm";
        excludePaths[2] = "/code/captcha-image";
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePaths);

        super.addInterceptors(registry);

    }


    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/assets/");
        super.addResourceHandlers(registry);
    }


}

