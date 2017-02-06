package com.cn.web.controller;

import com.cn.web.service.IUserImpl;
import com.cn.web.util.page.PageBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Describe:用户控制层
 * User:tangjing
 * Date:17/1/13
 * Time:下午2:22
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    PageBaseUtil pageBaseUtil;

    @Autowired
    private IUserImpl iUser;

    private static final String url="user/";

    @RequestMapping("/list.htm")
    public String toUserList(Model model) throws Exception
    {
        return url+"list";
    }

    @RequestMapping("/add.htm")
    public String toUserAdd(Model model) throws Exception
    {
        return url+"add";
    }



//    @RequestMapping(value="/tableDemoAjax.json")
    @RequestMapping(value="/tableDemoAjax.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String tableDemoAjax(@RequestParam String aoData) throws Exception {
        return iUser.queryUserListPage(aoData);
    }

}
