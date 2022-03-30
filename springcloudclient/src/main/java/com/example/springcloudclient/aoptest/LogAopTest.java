package com.example.springcloudclient.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description:日志aop
 * @USER: 梁思禹
 * @DATE: 2022/3/28
 */
@Component
@Aspect
public class LogAopTest {

    private final Logger logger = LoggerFactory.getLogger(LogAopTest.class);

    @Pointcut("execution(public * com.example.springcloudclient..*.controller..*.*(..))")
    public void LogController(){}

    @Before("LogController()")
    public void LogRequestBefore(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        StringBuilder requestLog = new StringBuilder();
        Signature signature = joinPoint.getSignature();
        logger.info("======日志前置切入开始=======");
        try{
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            logger.info("请求地址："+request.getRequestURL().toString());
            logger.info("请求IP："+request.getRemoteAddr());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("请求类方法："+joinPoint.getSignature());
        logger.info("请求方法参数值："+ Arrays.toString(joinPoint.getArgs()));
        //处理请求参数
        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0){
            requestLog.append("请求参数 = {}");
        }else{
            for (int i = 0; i < paramNames.length; i++) {
                requestLog.append("参数 = [").append(paramNames[i]).append(",").append(paramValues[i]).append("]");
            }
        }
        logger.info(requestLog.toString());
        logger.info("==========日志前置切入结束===========");
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After("LogController()")
    public void logAfterAdvice(JoinPoint joinPoint){
        try{
            String name = joinPoint.getSignature().getName();
            logger.info(System.currentTimeMillis()+" 后置通知:"+name);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Around("LogController()")
    public Object logAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        logger.info(System.currentTimeMillis()+" 环绕前置通知===");
        try {
            Object proceed = proceedingJoinPoint.proceed();
            logger.info(System.currentTimeMillis()+" 环绕后置通知，返回结果"+proceed);
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
