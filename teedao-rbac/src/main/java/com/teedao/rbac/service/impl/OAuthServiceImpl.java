package com.teedao.rbac.service.impl;

import com.teedao.rbac.service.OAuthCacheElement;
import com.teedao.rbac.service.OAuthService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthServiceImpl implements OAuthService {


    private Cache cache;

    @Autowired
    public OAuthServiceImpl(EhCacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    public void addAuthCode(String authCode, OAuthCacheElement element) {
        cache.put(authCode, element);
    }

    public void addAccessToken(String authCode, OAuthCacheElement element) {
        cache.put(authCode, element);
    }
    public OAuthCacheElement getElementByAuthCode(String authCode) {
        return (OAuthCacheElement)cache.get(authCode);
    }

    public OAuthCacheElement getElementByAccessToken(String accessToken) {
        return (OAuthCacheElement)cache.get(accessToken);
    }

    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    public long getExpireIn() {
        return 3600L;
    }


}
