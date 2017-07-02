package com.teedao.rbac.oauth2;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Set;

/**
 * oauth 认证返回数据，包含用户权限信息
 * @author Torry.Zhang
 * @since 2017/6/7
 */
public class OAuthUserInfo {
    private String siteNo;
    private String username;
    private String realname;
    private String mobile;
    private String email;
    private Set<String> roles;
    private Set<String> permissions;
    private List<Menu> menus;

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
