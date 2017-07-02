package com.teedao.rbac.aop;

import com.teedao.rbac.entity.Oplog;
import com.teedao.rbac.entity.User;
import com.teedao.rbac.result.BaseResult;
import com.teedao.rbac.service.OplogService;
import com.teedao.rbac.util.Const;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DingYS on 2017/3/31.
 * 日志管理
 */
@Aspect
@Service
public class LoggerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Autowired
    OplogService oplogService;

    @Before("within(com.teedao.rbac..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        logger.info("执行 " + loggerManage.description() + " 开始");
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParames(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "within(com.teedao.rbac..*) && @annotation(loggerManage)",returning="rvt")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage,Object rvt) {
        logger.info("返回报文:" + rvt);
        //记录操作日志
        if(loggerManage.isOperateEnable()){
            if(rvt instanceof BaseResult){
                User u = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER);
                String userIp = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_CUSTOM_IP);
                BaseResult br=(BaseResult)rvt;
                Oplog log=new Oplog();
                log.setUserId(u.getId());
                log.setIp(userIp);
                log.setSiteNo(Const.SITE_NO);
                log.setOperateType(joinPoint.getSignature().toShortString());
                log.setStatus(br.isSucceed()? "SUCCESS":"FAILED");
                log.setContent(parseParames(joinPoint.getArgs()));
                log.setCreateTime(System.currentTimeMillis());
                oplogService.createLog(log);
            }
        }
        logger.info("执行 " + loggerManage.description() + " 结束");
    }

    @AfterThrowing(pointcut = "within(com.teedao.rbac..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        logger.error("执行 " + loggerManage.description() + " 异常", ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数[{}] ");
        for (Object obj : parames) {
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }

}
