package com.teedao.rbac.result;

/**
 * Created by Zhang on 2015/9/29.
 * 返回结果集
 */
public class ResultInfo<T> extends BaseResult {

    private T data;

    public ResultInfo() {
        super();
    }

    public ResultInfo(BaseRet msg) {
        this.setRspCode(msg.getCode());
        this.setRspMsg(msg.getMsg());
    }


    public ResultInfo(BaseRet msg,T data) {
        this.setRspCode(msg.getCode());
        this.setRspMsg(msg.getMsg());
        this.data = data;
    }
    
    public ResultInfo(String code, String msg) {
        this.setRspCode(code);
        this.setRspMsg(msg);
    }


    
    public ResultInfo(String code, String msg, T data) {
        this.setRspCode(code);
        this.setRspMsg(msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

}
