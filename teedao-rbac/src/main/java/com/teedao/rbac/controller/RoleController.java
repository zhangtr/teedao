package com.teedao.rbac.controller;

import com.teedao.rbac.aop.LoggerManage;
import com.teedao.rbac.entity.Role;
import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.RoleParam;
import com.teedao.rbac.result.BaseResult;
import com.teedao.rbac.result.BaseRet;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.service.ResourceService;
import com.teedao.rbac.service.RoleService;
import com.teedao.rbac.service.SiteService;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    SiteService siteService;


    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String listPage(Model model) {
        List<Site> list= siteService.findAll();
        model.addAttribute("sites",list);
        return "rbac/role_list";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public DataTable<Role> list(RoleParam param) {
        logger.info(ToStringBuilder.reflectionToString(param));
        DataTable<Role> pager = roleService.getRolePage(param);
        if (pager != null) {
            return pager;
        } else {
            return new DataTable<Role>(param.getDraw(),0,new ArrayList<Role>());
        }
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/{siteNo}/create", method = RequestMethod.GET)
    public String showCreateForm(@PathVariable("siteNo") String siteNo, Model model) {
        Role role = new Role();
        role.setSiteNo(siteNo);
        String arr = resourceService.getZtreeJson(siteNo);
        model.addAttribute("resourceJson", arr);
        model.addAttribute("role", role);
        model.addAttribute("op", "create");
        return "rbac/role_edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "添加角色",isOperateEnable = true)
    public BaseResult create(RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        boolean flag = roleService.createRole(role);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findOne(id);
        String arr = resourceService.getZtreeJson(role.getSiteNo());
        model.addAttribute("resourceJson", arr);
        model.addAttribute("role", role);
        model.addAttribute("op", "update");
        return "rbac/role_edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "修改角色",isOperateEnable = true)
    public BaseResult update(@PathVariable("id") Long id, RoleParam param) {
        logger.info(ToStringBuilder.reflectionToString(param));
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        role.setId(id);
        boolean flag = roleService.updateRole(role);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "删除角色",isOperateEnable = true)
    public BaseResult delete(@PathVariable("id") Long id) {
        boolean flag = roleService.deleteRole(id);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

}
