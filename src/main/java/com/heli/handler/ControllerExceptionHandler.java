package com.heli.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 拦截异常 返回指定error页面
 * date: 2021/1/30 10:57
 * @author heli
 * @since JDK 1.8
 */

/**
 * 拦截有Controller注解的控制器
 * @author heli
 */
@ControllerAdvice
public class ControllerExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 用来标注方法可以来处理异常，只要是Exception
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录异常信息，打印在控制台
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);

        //判断是否存在带@ResponseStatus注解的异常类，如果 不为null（存在），那么就抛出此异常 返回error页面；否则交给springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        //返回的页面path
        mv.setViewName("error/error");
        return mv;
    }
}
