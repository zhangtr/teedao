package com.teedao.rbac.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author ZQ
 * @version 1.0
 * @date 2015-9-10
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 返回信息码
     */
    private String rspCode="000000";
    /**
     * 返回信息内容
     */
    private String rspMsg="成功";

    public BaseResult() {
    }

    public BaseResult(BaseRet msg) {
        this.setRspCode(msg.getCode());
        this.setRspMsg(msg.getMsg());
    }

    public BaseResult(String rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    public String getRspCode() {
        return rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public  boolean isSucceed(){
        boolean flag=false;
        if(BaseRet.SUCCESS.getCode().equals(this.rspCode)){
            flag=true;
        }
        return flag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
