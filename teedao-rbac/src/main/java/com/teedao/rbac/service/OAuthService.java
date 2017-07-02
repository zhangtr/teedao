package com.teedao.rbac.service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
public interface OAuthService {

    //添加 auth code
    public void addAuthCode(String authCode, OAuthCacheElement element);

    //添加 access token
    public void addAccessToken(String authCode, OAuthCacheElement element);


    //验证auth code是否有效
    boolean checkAuthCode(String authCode);
    //验证access token是否有效
    boolean checkAccessToken(String accessToken);

    OAuthCacheElement getElementByAuthCode(String authCode);
    OAuthCacheElement getElementByAccessToken(String accessToken);

    //auth code / access token 过期时间
    long getExpireIn();
}
