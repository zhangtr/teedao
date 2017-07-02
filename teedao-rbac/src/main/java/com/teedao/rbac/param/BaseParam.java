package com.teedao.rbac.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Zhang on 2016-8-21.
 */
public class BaseParam implements Serializable {
    private static final long serialVersionUID = 5737490581520081614L;
    private String ip;
    private Long userId; //操作员
    private String systemNo = "zx-rbac";//默认来源系统编号

    private Integer currentPage = 1; //当前页
    private Integer pageSize = 15; //页大小
    private Integer beginLine; //开始行

    public BaseParam() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getBeginLine() {
        return (currentPage - 1) * pageSize;
    }

    public void setBeginLine(Integer beginLine) {
        this.beginLine = beginLine;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
