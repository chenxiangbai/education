package com.education.aspect;

import com.education.annoation.AspectLog;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author admin
 * @Date 2021-02-22 14:44
 * @Version 1.0
 * @Description 日志切面
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.education.annoation.AspectLog)")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AspectLog logs = method.getAnnotation(AspectLog.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("请求时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n");
        stringBuffer.append("请求ip  : " + request.getRemoteAddr() + "\n");
        stringBuffer.append("请求Url : " + request.getRequestURL().toString() + "\n");
        stringBuffer.append("请求方式 : " + request.getMethod() + "\n");
        stringBuffer.append("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "\n");
        stringBuffer.append("请求方法名:" + logs.value() + "\n");
        Object[] objects = joinPoint.getArgs();
        stringBuffer.append("请求参数 : ");
        for (Object object : objects) {
            if (ObjectUtils.isNotEmpty(object)) {
                stringBuffer.append(JSON.toJSONString(object));
            }
        }
        stringBuffer.append("\n");
        // 系统信息
        stringBuffer.append("浏览器：" + userAgent.getBrowser() + "\n");
        stringBuffer.append("浏览器版本：" + userAgent.getBrowserVersion() + "\n");
        stringBuffer.append("操作系统: " + userAgent.getOperatingSystem() + "\n");
        log.info(stringBuffer.toString());
    }

    @AfterReturning(returning = "returnValue", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AspectLog logs = method.getAnnotation(AspectLog.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("返回时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n");
        stringBuffer.append("请求Url : " + request.getRequestURL().toString() + "\n");
        stringBuffer.append("请求方式 : " + request.getMethod() + "\n");
        stringBuffer.append("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "\n");
        stringBuffer.append("请求方法名:" + logs.value() + "\n");
        stringBuffer.append("返回参数 : " + JSON.toJSONString(returnValue) + "\n");
        log.info(stringBuffer.toString());
    }

}
