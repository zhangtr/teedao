package com.teedao.rbac.service.impl;

import com.teedao.rbac.service.ResourceService;
import com.teedao.rbac.dao.ResourceMapper;
import com.teedao.rbac.entity.Resource;
import com.teedao.rbac.util.JsonUtil;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    public boolean createResource(Resource resource) {
        resource.setCreateTime(System.currentTimeMillis());
        resource.setLastModifyTime(System.currentTimeMillis());
        return resourceMapper.insertSelective(resource) > 0 ? true : false;
    }


    public boolean updateResource(Resource resource) {
        resource.setLastModifyTime(System.currentTimeMillis());
        return resourceMapper.updateByPrimaryKeySelective(resource) > 0 ? true : false;
    }


    public boolean deleteResource(Long resourceId) {
        return resourceMapper.deleteByPrimaryKey(resourceId) > 0 ? true : false;
    }

    public Resource findOne(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }


    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }


    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }


    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        List<Resource> roots = new ArrayList<Resource>();
        Set<Long> pIds = new HashSet<Long>();
        for (Resource resource : allResources) {
            if (resource.isRootNode()) {
                roots.add(resource);
                continue;
            }
            if (!resource.getType().equals("menu")) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            pIds.add(resource.getParentId());
            menus.add(resource);
        }
        for (Resource res : roots) {
            if (pIds.contains(res.getId())) {
                menus.add(res);
            }
        }
        return menus;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions, String siteNo) {
        List<Resource> allResources = resourceMapper.selectBySiteNo(siteNo);
        List<Resource> menus = new ArrayList<Resource>();
        List<Resource> roots = new ArrayList<Resource>();
        Set<Long> pIds = new HashSet<Long>();
        for (Resource resource : allResources) {
            if (resource.isRootNode()) {
                roots.add(resource);
                continue;
            }
            if (!resource.getType().equals("menu")) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            pIds.add(resource.getParentId());
            menus.add(resource);
        }
        for (Resource res : roots) {
            if (pIds.contains(res.getId())) {
                menus.add(res);
            }
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getZtreeJson(String siteNo) {
        List<Resource> list = resourceMapper.selectBySiteNo(siteNo);
        List<Map<String ,Object>> arr = new ArrayList();
        for (Resource resource : list) {
            Map<String ,Object> obj = new HashMap<>();
            obj.put("id", resource.getId());
            obj.put("pId", resource.getParentId());
            obj.put("name", resource.getName());
            if (resource.getParentId() == 0) {
                obj.put("open", true);
                obj.put("nocheck", true);
            }
            if (!resource.getAvailable()) {
                obj.put("chkDisabled", true);
            }
            arr.add(obj);
        }
        return JsonUtil.beanToJson(arr);
    }


    @Override
    public List<Resource> getTreeList(String siteNo) {
        List<Resource> list = resourceMapper.selectBySiteNo(siteNo);
        List<Resource> result = new ArrayList<Resource>();
        List<Resource> father = new ArrayList<Resource>();
        List<Resource> children = new ArrayList<Resource>();
        for (Resource resource : list) {
            if (resource.getParentId() == 0) {
                father.add(resource);
            } else {
                children.add(resource);
            }
        }
        for (Resource resource : father) {

            result = iter(resource, result, children);
        }
        return result;
    }


    private List<Resource> iter(Resource fr, List<Resource> result,
                                List<Resource> children) {
        result.add(fr);
        for (Resource resource : children) {
            if (resource.getParentId().longValue() == fr.getId().longValue()) {
                if (resource.getType().equals("button")) {
                    result.add(resource);
                } else {
                    result = iter(resource, result, children);
                }
            }
        }
        return result;
    }
}
