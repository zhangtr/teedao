package com.teedao.rbac.result;

/**
 * 返回码封装类
 * @author Torry.Zhang
 * @since 2017/4/26
 */
public class BaseRet {
    /**
     * 返回信息码
     */
    private String code;
    /**
     * 返回信息内容
     */
    private String msg;

    public BaseRet() {
        super();
    }

    public BaseRet(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BaseRet SUCCESS = new BaseRet("000000","成功");
    public static BaseRet FAILED = new BaseRet("999999","失败");
    public static BaseRet PARAM_ERROR = new BaseRet("000001","参数错误");
    public static BaseRet CALL_REMOTE_FAILED=new BaseRet("000002", "远程服务调用失败");
    public static BaseRet SYSTEM_ERROR=new BaseRet("000003", "系统错误");

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
