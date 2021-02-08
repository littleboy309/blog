package com.heli.config;

import com.heli.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 登录拦截配置类
 * date: 2021/1/31 13:07
 *
 * @author heli
 * @since JDK 1.8
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器加载过来
        registry.addInterceptor(new LoginInterceptor())
                //增加拦截路径
                .addPathPatterns("/admin/**")
                //排除哪些不需要拦截
                .excludePathPatterns("/admin").excludePathPatterns("/admin/login");
    }
}
