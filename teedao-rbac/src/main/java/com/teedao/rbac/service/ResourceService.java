package com.teedao.rbac.service;

import com.teedao.rbac.entity.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    public boolean createResource(Resource resource);
    public boolean updateResource(Resource resource);
    public boolean deleteResource(Long resourceId);

    public Resource findOne(Long resourceId);
    public List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    public Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    public List<Resource> findMenus(Set<String> permissions);

    public List<Resource> findMenus(Set<String> permissions, String siteNo);
    
	public String getZtreeJson(String siteNo);
	
	public List<Resource> getTreeList(String siteNo);
}
