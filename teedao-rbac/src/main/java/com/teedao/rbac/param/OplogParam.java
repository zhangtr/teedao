package com.teedao.rbac.param;

/**
 * Created by Zhang on 2016-8-21.
 */
public class OplogParam extends DataTableParam {

    private String siteNo;

    private String content;

    private String operateType;

    private String status;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
