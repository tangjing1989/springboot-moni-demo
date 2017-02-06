package com.cn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Describe:实例控制层
 * User:tangjing
 * Date:17/1/13
 * Time:下午3:23
 */
@Controller
@RequestMapping("/example/")
public class ExampleController {

    private static final String url = "example/";

    @RequestMapping("example.htm")
    public String toExample() {
        return url + "example";
    }

    @RequestMapping("table.htm")
    public String toTable() {
        return url + "table";
    }

    @RequestMapping("icon.htm")
    public String toIcon() {
        return url + "icon";
    }

}
