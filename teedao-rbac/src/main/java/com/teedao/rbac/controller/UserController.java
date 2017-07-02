package com.teedao.rbac.controller;

import com.teedao.rbac.aop.LoggerManage;
import com.teedao.rbac.entity.User;
import com.teedao.rbac.param.UserParam;
import com.teedao.rbac.result.BaseResult;
import com.teedao.rbac.result.BaseRet;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.service.RoleService;
import com.teedao.rbac.service.SiteService;
import com.teedao.rbac.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    SiteService siteService;
    @Autowired
    RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String listPage(Model model) {
        return "rbac/user_list";
    }

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public DataTable<User> list(UserParam param) {
        DataTable<User> pager = userService.getUserPage(param);
        if (pager != null) {
            return pager;
        } else {
            return new DataTable<User>(param.getDraw(),0,new ArrayList<User>());
        }
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("op", "create");
        model.addAttribute("user", new User());
        return "rbac/user_edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "添加用户",isOperateEnable = true)
    public BaseResult create(UserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        boolean flag = userService.createUser(user);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("op", "update");
        model.addAttribute("user", userService.findOne(id));
        return "rbac/user_edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "修改用户",isOperateEnable = true)
    public BaseResult update(@PathVariable("id") Long id, UserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        user.setId(id);
        boolean flag = userService.updateUser(user);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "删除用户",isOperateEnable = true)
    public BaseResult delete(@PathVariable("id") Long id) {
        boolean flag = userService.deleteUser(id);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String changePasswordPage(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        return "rbac/user_change_password";
    }
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "修改密码",isOperateEnable = true)
    public BaseResult changePassword(@PathVariable("id") Long id, String newPassword) {
        boolean flag = userService.changePassword(id, newPassword);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }
    private void setCommonData(Model model) {
        model.addAttribute("sites", siteService.findAll());
        model.addAttribute("roles", roleService.findAll());
    }
}
