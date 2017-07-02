package com.teedao.admin.controller;


import com.teedao.admin.util.Const;
import com.teedao.rbac.oauth2.OAuthUserInfo;
import com.teedao.rbac.util.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest req) {
        OAuthUserInfo user = (OAuthUserInfo) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("menus", JsonUtil.beanToJson(user.getMenus()));
        SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, user);
        return "index";
    }

}
