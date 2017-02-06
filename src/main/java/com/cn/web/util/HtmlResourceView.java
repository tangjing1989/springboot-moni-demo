package com.cn.web.util;


import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.io.File;
import java.util.Locale;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/16
 * Time:下午5:18
 * 重写resolveViewName方法实现多视图的方法
 */
public class HtmlResourceView extends ThymeleafViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {

        SpringResourceTemplateResolver servletContextTemplateResolver = (SpringResourceTemplateResolver) this.getWebApplicationContext().getBean("templateResolver");
        //servletContextTemplateResolver.initialize();
        String prefix = servletContextTemplateResolver.getPrefix();
        String suffix = servletContextTemplateResolver.getSuffix();
        File   file   = new File(this.getServletContext().getRealPath("/") +prefix+ viewName + suffix);

        return !file.exists()?null:super.resolveViewName(viewName, locale);
    }
}
