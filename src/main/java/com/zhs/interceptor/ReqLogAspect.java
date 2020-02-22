/*
package com.zhs.interceptor;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * 拦截请求记录日志 Created by xy on 17/6/17.
 *//*

@Aspect
@Order(1)
@Component
public class ReqLogAspect {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.zhs.controller.*(..))")
    public void webLog() {
    }

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<String> requestInfo = new ThreadLocal<>();


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();



        logger.info(requestInfo.get() + " ARGS " + printArray(joinPoint.getArgs()) + " " + getParams(request));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 记录返回结果
        logger.info(requestInfo.get() + " RETURN " + (System.currentTimeMillis() - startTime.get()) + " " + JSON.toJSONString(ret));
        startTime.remove();
        requestInfo.remove();
    }


    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        logger.warn(requestInfo.get() + " THROWERROR OtherException " + (System.currentTimeMillis() - startTime.get()) , e);
        startTime.remove();
        requestInfo.remove();
    }
    //判断是否已经登录
    private boolean isLogin(HttpServletRequest request) {
        return true;
    }

    private String getParams(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if("questionString".equals(paramName)){
                continue;
            }
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map.toString();

    }

    private String printArray(Object[] objs){
        StringBuilder rt = new StringBuilder("[");
        int count = 0;
        for(Object o : objs){
            if(count>0){
                rt.append(",");
            }
            if(o!=null && o.getClass()!=null && o.getClass().isArray()){
                rt.append(Arrays.toString((Object[]) o));
            }else {
                if (o!=null && o.toString().length()>1200){
                    rt.append(o.toString(), 0, 1000);
                }else {
                    rt.append(o);
                }
            }
            count++;
        }
        return rt+"]";
    }

}
*/
