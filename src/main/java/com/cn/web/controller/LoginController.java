package com.cn.web.controller;

import com.cn.web.pojo.UserPojo;
import com.cn.web.service.ISysImpl;
import com.cn.web.service.IUserImpl;
import com.cn.web.util.Global;
import com.cn.web.util.Message;
import com.cn.web.util.SpringContextService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/10
 * Time:下午4:17
 */

@Controller
public class LoginController {

    @Autowired
    private IUserImpl iUserImpl;
    @Autowired
    private ISysImpl  iSysImpl;

    private static final String kapthaCode = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


    @RequestMapping({"/welcome.htm","/"})
    public String doWelcome(Model model) {
        model.addAttribute("enableVerif", SpringContextService.IS_KAPTCHA);
        return "/login";
    }

    @RequestMapping("/login.htm")
    public String doLogin(HttpServletRequest request, String userName, String password, String verifCode, Model model) {
        try {
            //判断用户有无session信息
            if (request.getSession().getAttribute(Global.USER_SESSION_KEY) != null) {
                return "redirect:/main.htm";
            }
            //判断是否启用验证码功能
            if (SpringContextService.IS_KAPTCHA.equals("true")) {
                model.addAttribute("enableVerif", kapthaCode);
                //判断验证码是否为空
                if (StringUtils.isEmpty(verifCode)) {
                    Message.getMessage(model, "VERIFCODE_NULL");
                    return "forward:/welcome.htm";
                }
                //判断验证码是否正确
                String kaptchaExpected = (String) request.getSession().getAttribute(kapthaCode);
                if (!verifCode.equals(kaptchaExpected)) {
                    Message.getMessage(model, "VERIFCODE_ERROR");
                    return "forward:/welcome.htm";
                }
            }

            //判断是否存在该用户
            UserPojo user1 = iUserImpl.getUser(userName, "");
            if (user1 == null) {
                Message.getMessage(model, "USER_NOT_EXIST");
                return "forward:/welcome.htm";
            }
            //判断用户名密码是否正确
            UserPojo user2 = iUserImpl.getUser(userName, password);
            if (user2 == null) {
                Message.getMessage(model, "lOGIN_ERROR");
                return "forward:/welcome.htm";
            }
            request.getSession().setAttribute(Global.USER_SESSION_KEY, user2);
            return "redirect:/main.htm";
        } catch (Exception e) {
            Message.getMessage(model, "UNlOGIN_ERROR");
            return "forward:/welcome.htm";
        }

    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute(Global.USER_SESSION_KEY);
        return "redirect:/";
    }


    @RequestMapping("/main.htm")
    public String main(Model model, HttpServletRequest request) {
        model.addAttribute("menu1s", iSysImpl.queryLev1Menu());
        model.addAttribute("user", request.getSession().getAttribute(Global.USER_SESSION_KEY));
        return "index";
    }

}
