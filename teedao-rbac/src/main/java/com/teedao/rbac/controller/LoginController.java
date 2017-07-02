package com.teedao.rbac.controller;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zhang on 2016-8-26.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "该用户不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        }else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "失败次数过多，请稍候再试";
        }else if(LockedAccountException.class.getName().equals(exceptionClassName)){
            error = "账号冻结，请联系管理员";
        }else if (exceptionClassName != null) {
            error = "登录异常";
        }
        model.addAttribute("error", error);
        return "login";
    }
}
