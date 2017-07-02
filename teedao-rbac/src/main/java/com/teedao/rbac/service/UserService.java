package com.teedao.rbac.service;

import com.teedao.rbac.entity.User;
import com.teedao.rbac.oauth.OAuthUserInfo;
import com.teedao.rbac.param.UserParam;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.entity.Resource;

import java.util.List;
import java.util.Set;

public interface UserService {
    /**
     * 创建用户
     *
     * @param User
     */
    boolean createUser(User User);

    boolean updateUser(User User);

    boolean deleteUser(Long UserId);

    /**
     * 修改密码
     *
     * @param UserId
     * @param newPassword
     */
    boolean changePassword(Long UserId, String newPassword);


    User findOne(Long UserId);


    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     *
     * @param siteNo
     * @param username
     * @return
     */
    Set<String> findRoles(String username, String siteNo);

    /**
     * 根据用户名查找其权限
     *
     * @param siteNo
     * @param username
     * @return
     */
    Set<String> findPermissions(String username, String siteNo);

    DataTable<User> getUserPage(UserParam params);

    List<Resource> findMenus(String username, String siteNo);

    OAuthUserInfo findOAuthUserInfo(OAuthCacheElement element);
}
