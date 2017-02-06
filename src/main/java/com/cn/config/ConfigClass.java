package com.cn.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
/**
 * Describe:
 * User:tangjing
 * Date:2017/2/5
 * Time:下午7:39
 */


/**
 * classpath路径：locations={"classpath:application-bean1.xml","classpath:application-bean2.xml"}
 * file路径： locations = {"file:d:/test/application-bean1.xml"};
 */
@Configuration
@ImportResource(locations = {"classpath:spring-common.xml"})
public class ConfigClass {

}