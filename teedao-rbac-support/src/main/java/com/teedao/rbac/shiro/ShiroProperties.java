package com.teedao.rbac.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限配置相关参数
 * @author Torry.Zhang
 * @since 2017/6/8
 */
@Component
@ConfigurationProperties(prefix = "shiro.oauth2")
public class ShiroProperties {

    private String clientId;
    private String clientSecret;
    private String serverHost;
    private String clientHost;
    private List<String> staticUrls;
    //sessionID 名称，各系统不一样，防止覆盖
    private String sidName;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }


    public List<String> getStaticUrls() {
        return staticUrls;
    }

    public void setStaticUrls(List<String> staticUrls) {
        this.staticUrls = staticUrls;
    }

    public String getSidName() {
        return sidName;
    }

    public void setSidName(String sidName) {
        this.sidName = sidName;
    }
}
