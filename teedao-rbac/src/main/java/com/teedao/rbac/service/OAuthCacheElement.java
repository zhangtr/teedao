package com.teedao.rbac.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Torry.Zhang
 * @since 2017/6/7
 */
public class OAuthCacheElement implements Serializable {

    private static final long serialVersionUID = -6777676885513444954L;
    private String username;
    private String siteNo;

    public OAuthCacheElement(String username, String siteNo) {
        this.username = username;
        this.siteNo = siteNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
