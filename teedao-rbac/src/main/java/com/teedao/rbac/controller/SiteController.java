package com.teedao.rbac.controller;

import com.teedao.rbac.aop.LoggerManage;
import com.teedao.rbac.result.BaseResult;
import com.teedao.rbac.result.BaseRet;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.SiteParam;
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


@Controller
@RequestMapping("/site")
public class SiteController{
    private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SiteService siteService;

    @RequiresPermissions("site:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "rbac/site_list";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public DataTable<Site> list(SiteParam param) {
        logger.info(ToStringBuilder.reflectionToString(param));
        DataTable<Site> pager = siteService.getSitePage(param);
        if (pager != null) {
            return pager;
        } else {
            return new DataTable<Site>(param.getDraw(),0,new ArrayList<Site>());
        }
    }

    @RequiresPermissions("site:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("site", new Site());
        model.addAttribute("op", "create");
        return "rbac/site_edit";
    }

    @RequiresPermissions("site:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "添加站点",isOperateEnable = true)
    public BaseResult create(SiteParam siteParam) {
        Site site = new Site();
        BeanUtils.copyProperties(siteParam, site);
        boolean flag = siteService.createSite(site);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("site:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("site", siteService.findOne(id));
        model.addAttribute("op","update");
        return "rbac/site_edit";
    }

    @RequiresPermissions("site:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "修改站点",isOperateEnable = true)
    public BaseResult update(SiteParam siteParam,@PathVariable("id") Long id) {
        Site site = new Site();
        BeanUtils.copyProperties(siteParam, site);
        site.setId(id);
        boolean flag= siteService.updateSite(site);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

    @RequiresPermissions("site:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "删除站点",isOperateEnable = true)
    public BaseResult delete(@PathVariable("id") Long id) {
        boolean flag= siteService.deleteSite(id);
        if (flag) {
            return new BaseResult(BaseRet.SUCCESS);
        }
        return new BaseResult(BaseRet.FAILED);
    }

}
