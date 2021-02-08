package com.heli.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Description: 日志 处理
 * date: 2021/1/30 12:19
 *
 * @author heli
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(* com.heli.controller.*.*(..))")
    public void log(){}

    /**
    * Description: 记录请求
    * @author: heli
    * @date: 2021/1/30 13:26
    * @param:
    * @return:
    */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        log.info("Request : {}", requestLog);
    }

    @After("log()")
    public void doAfter(){
//        log.info("---------doAfter---------");
    }

    /**
    * Description: 最终返回
    * @author: heli
    * @date: 2021/1/30 13:29
    * @param:
    * @return:
    */
    @AfterReturning(returning = "result",pointcut = "log()")
    public  void doAfterReturn(Object result){
        log.info("Result : {}" , result);
    }

    /**定义个内部类*/
    @AllArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
