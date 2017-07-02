package com.teedao.rbac.aop;

import java.lang.annotation.*;

/**
 * Created by DingYS on 2017/3/31.
 * 日志注释
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
    boolean isOperateEnable() default false;
}
