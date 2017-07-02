package com.teedao.rbac.service.impl;

import com.teedao.rbac.dao.RoleMapper;
import com.teedao.rbac.dao.UserMapper;
import com.teedao.rbac.entity.Role;
import com.teedao.rbac.entity.User;
import com.teedao.rbac.oauth.Menu;
import com.teedao.rbac.oauth.OAuthUserInfo;
import com.teedao.rbac.param.UserParam;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.service.OAuthCacheElement;
import com.teedao.rbac.service.ResourceService;
import com.teedao.rbac.dao.ResourceMapper;
import com.teedao.rbac.entity.Resource;
import com.teedao.rbac.service.PasswordHelper;
import com.teedao.rbac.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ResourceService resourceService;
    @Autowired
    PasswordHelper passwordHelper;

    /**
     * 创建用户
     *
     * @param user
     */
    public boolean createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        user.setCreateTime(System.currentTimeMillis());
        user.setLastModifyTime(System.currentTimeMillis());
        return userMapper.insertSelective(user) > 0 ? true : false;
    }


    public boolean updateUser(User user) {
        user.setLastModifyTime(System.currentTimeMillis());
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i > 0 ? true : false;
    }

    public boolean deleteUser(Long userId) {
        return userMapper.deleteByPrimaryKey(userId) > 0 ? true : false;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    public boolean changePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        user.setLastModifyTime(System.currentTimeMillis());
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? true : false;
    }


    public User findOne(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public Set<String> findRoles(String username, String siteNo) {
        Set<String> set = new HashSet<String>();
        User user = findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        StringBuilder sb = new StringBuilder("('");
        sb.append(user.getRoleIds().replace(",", "','"));
        sb.append("')");
        List<Role> list = roleMapper.selectListByRoleIds(sb.toString());
        for (Role role : list) {
            if (role.getAvailable() && siteNo.equals(role.getSiteNo())) {
                set.add(role.getRole());
            }
        }
        return set;
    }

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public Set<String> findPermissions(String username, String siteNo) {
        Set<String> roleSet = new HashSet<String>();
        User User = findByUsername(username);
        if (User == null) {
            return Collections.EMPTY_SET;
        }
        StringBuilder sbb = new StringBuilder("('");
        sbb.append(User.getRoleIds().replace(",", "','"));
        sbb.append("')");
        List<Role> roleList = roleMapper.selectListByRoleIds(sbb.toString());
        for (Role role : roleList) {
            if (siteNo.equals(role.getSiteNo())) {
                String[] resourceIds = role.getResourceIds().split(",");
                for (String str : resourceIds) {
                    roleSet.add(str);
                }
            }
        }
        if (roleSet.size() < 1) {
            return Collections.EMPTY_SET;
        }
        StringBuilder sb = new StringBuilder("('");
        for (String str : roleSet) {
            sb.append(str).append("','");
        }
        sb.delete(sb.length() - 3, sb.length());
        sb.append("')");
        Set<String> permissionSet = new HashSet<String>();
        List<Resource> resourceList = resourceMapper.selectListByResourceIds(sb.toString());
        for (Resource resource : resourceList) {
            if (resource.getAvailable() && StringUtils.isNotBlank(resource.getPermission())) {
                permissionSet.add(resource.getPermission());
            }
        }
        return permissionSet;
    }

    @Override
    public DataTable<User> getUserPage(UserParam params) {
        int count = userMapper.selectPageCount(params);
        List<User> list = userMapper.selectPageList(params);
        DataTable<User> pager = new DataTable<>(params.getDraw(),count, list);
        return pager;
    }

    @Override
    public List<Resource> findMenus(String username, String siteNo) {
        Set<String> s = findPermissions(username, siteNo);
        List<Resource> rs = resourceService.findMenus(s);
        return rs;
    }

    @Override
    public OAuthUserInfo findOAuthUserInfo(OAuthCacheElement element) {
        User u=findByUsername(element.getUsername());
        Set<String> roles=findRoles(element.getUsername(),element.getSiteNo());
        Set<String> permissions=findPermissions(element.getUsername(),element.getSiteNo());
        List<Resource> resources=resourceService.findMenus(permissions,element.getSiteNo());
        List<Menu> menus=new ArrayList<>();
        for(Resource r:resources){
            Menu m=new Menu();
            BeanUtils.copyProperties(r,m);
            menus.add(m);
        }
        OAuthUserInfo userInfo=new OAuthUserInfo();
        userInfo.setSiteNo(element.getSiteNo());
        userInfo.setUsername(u.getUsername());
        userInfo.setEmail(u.getEmail());
        userInfo.setMobile(u.getMobile());
        userInfo.setRealname(u.getRealname());
        userInfo.setRoles(roles);
        userInfo.setPermissions(permissions);
        userInfo.setMenus(menus);
        return userInfo;
    }
}
