package com.teedao.rbac.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.teedao.rbac.oauth2.OAuth2AuthenticationFilter;
import com.teedao.rbac.oauth2.OAuth2Realm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhang on 2016-8-24.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroProperties shiroProperties() {
        return new ShiroProperties();
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Primary
    @DependsOn(value = "shiroProperties")
    @Bean(name = "oAuth2Realm")
    public OAuth2Realm oAuth2Realm() {
        OAuth2Realm realm = new OAuth2Realm();
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");
        realm.setClientId(shiroProperties().getClientId());
        realm.setClientSecret(shiroProperties().getClientSecret());
        realm.setAccessTokenUrl(shiroProperties().getServerHost() + "/accessToken");
        realm.setUserInfoUrl(shiroProperties().getServerHost() + "/userInfo");
        realm.setRedirectUrl(shiroProperties().getClientHost() + "/oauth2-login");
        return realm;
    }

    @Bean(name = "cacheManager")
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }

    @Bean(name = "sessionIdCookie")
    @DependsOn(value = "shiroProperties")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(shiroProperties().getSidName());
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(43200);//12小时
        return simpleCookie;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sm = new DefaultWebSessionManager();
        sm.setGlobalSessionTimeout(3600000);    //session失效时间60分钟
        sm.setSessionIdCookie(sessionIdCookie());
        sm.setSessionIdUrlRewritingEnabled(false);
        return sm;
    }

    //OAuth2身份验证过滤器
    public OAuth2AuthenticationFilter oAuth2AuthenticationFilter() {
        OAuth2AuthenticationFilter oaf = new OAuth2AuthenticationFilter();
        oaf.setAuthcCodeParam("code");
        oaf.setFailureUrl("/oauth2Failure");
        return oaf;
    }

    @DependsOn(value = "shiroProperties")
    public LogoutFilter logoutFilter() {
        LogoutFilter lf = new LogoutFilter();
        lf.setRedirectUrl(shiroProperties().getServerHost() + "/logout");
        return lf;
    }

    @Bean(name = "shiroFilter")
    @DependsOn(value = "shiroProperties")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        String loginUrl = shiroProperties().getServerHost() + "/authorize?client_id=" + shiroProperties().getClientId() + "&response_type=code&redirect_uri=" + shiroProperties().getClientHost() + "/oauth2-login";
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setSuccessUrl("/");
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("oauth2Authc", oAuth2AuthenticationFilter());
        filters.put("oauth2Logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> chains = new LinkedHashMap<String, String>();
        chains.put("/oauth2Failure", "anon");
        chains.put("/oauth2-login", "oauth2Authc");
        chains.put("/logout", "oauth2Logout");
        List<String> staticUrls=shiroProperties().getStaticUrls();
        for(String url:staticUrls){
            chains.put(url, "anon");
        }
        chains.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
        return shiroFilterFactoryBean;
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
