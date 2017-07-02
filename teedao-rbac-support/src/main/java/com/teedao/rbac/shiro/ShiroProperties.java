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

    private String clientId;//客户端id
    private String clientSecret;//客户端密钥
    private String serverHost; //服务端地址
    private String clientHost; //客户端地址，用于回调
    private List<String> staticUrls; //不需要shiro过滤的静态资源请求
    private String sidName;    //sessionID 名称，各系统不一样，防止覆盖



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
