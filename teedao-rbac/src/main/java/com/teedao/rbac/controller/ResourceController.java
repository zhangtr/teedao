package com.teedao.rbac.controller;

import com.teedao.rbac.service.ResourceService;
import com.teedao.rbac.aop.LoggerManage;
import com.teedao.rbac.entity.Resource;
import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.ResourceParam;
import com.teedao.rbac.result.BaseResult;
import com.teedao.rbac.result.BaseRet;
import com.teedao.rbac.service.SiteService;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/resource")
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    ResourceService resourceService;
    @Autowired
    SiteService siteService;

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request,Model model) {
        String siteNo=request.getParameter("siteNo");
        if(StringUtils.isBlank(siteNo)){
            Cookie[] cookies=request.getCookies();
            for(int i=0;i<cookies.length;i++){
                String name=cookies[i].getName();
                if("current_site".equals(name)){
                    siteNo=cookies[i].getValue();
                }
            }
        }
        List<Resource> list = resourceService.getTreeList(siteNo);
        List<Site> sites= siteService.findAll();
        model.addAttribute("resourceList", list);
        model.addAttribute("sites",sites);
        return "rbac/resource_list";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "添加资源目录")
    public BaseResult createRootMene(ResourceParam param) {
        if (StringUtils.isNotBlank(param.getName())) {
            param.setAvailable(true);
            param.setParentId((long) 0);
            param.setUrl("#");
            param.setType("menu");
            Resource resource = new Resource();
            BeanUtils.copyProperties(param, resource);
            boolean flag = resourceService.createResource(resource);
            if (flag) {
                return new BaseResult(BaseRet.SUCCESS);
            }
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/append", method = RequestMethod.GET)
    public String showAppendForm(@PathVariable("parentId") Long parentId, Model model) {
        Resource parent = resourceService.findOne(parentId);
        Resource child = new Resource();
        child.setParentId(parentId);
        model.addAttribute("parent", parent);
        model.addAttribute("resource", child);
        model.addAttribute("op", "create");
        return "rbac/resource_edit";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/append", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "添加资源",isOperateEnable = true)
    public BaseResult create(@PathVariable("parentId") Long parentId, ResourceParam param) {
        Resource parent = resourceService.findOne(parentId);
        param.setSiteNo(parent.getSiteNo());
        Resource resource = new Resource();
        BeanUtils.copyProperties(param, resource);
        resource.setSiteNo(parent.getSiteNo());
        boolean flag = resourceService.createResource(resource);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("parent", new Resource());
        model.addAttribute("resource", resourceService.findOne(id));
        model.addAttribute("op", "update");
        return "rbac/resource_edit";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "修改资源",isOperateEnable = true)
    public BaseResult update(@PathVariable("id") Long id, ResourceParam param) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(param, resource);
        resource.setId(id);
        boolean flag = resourceService.updateResource(resource);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "删除资源",isOperateEnable = true)
    public BaseResult delete(@PathVariable("id") Long id) {
        boolean flag = resourceService.deleteResource(id);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }
}
