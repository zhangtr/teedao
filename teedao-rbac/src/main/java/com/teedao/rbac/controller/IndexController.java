package com.teedao.rbac.controller;

import com.teedao.rbac.entity.Resource;
import com.teedao.rbac.entity.User;
import com.teedao.rbac.service.UserService;
import com.teedao.rbac.util.Const;
import com.teedao.rbac.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest req) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<Resource> menus = userService.findMenus(username, Const.SITE_NO);
        model.addAttribute("menus", JsonUtil.beanToJson(menus));
        logger.info(username+":"+JsonUtil.beanToJson(menus));
        //把用户信息放入session
        User u = userService.findByUsername(username);
        SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, u);
        SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_CUSTOM_IP, getUserIp(req));
        return "index";
    }

    protected String getUserIp(HttpServletRequest request) {
        String value = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return request.getRemoteAddr();
        }
    }

}
