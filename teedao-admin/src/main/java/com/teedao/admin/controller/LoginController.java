package com.teedao.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {
    /**
     * 授权失败返回页面
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/oauth2Failure")
    public String showLoginForm(HttpServletRequest req, Model model) {
        return "oauth2Failure";
    }

    @RequestMapping(value = "/demo")
    public String showDemoPage(HttpServletRequest req, Model model) {
        return "demo";
    }


}
