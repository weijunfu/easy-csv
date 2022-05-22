package com.ijunfu.easycsv.autoconfig.config;

import com.ijunfu.easycsv.autoconfig.interceptor.PathLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title :
 * @Remarks:
 * @Author : Weizhiguo
 * @Version: 1.0.0
 * @Date : 2022-05-22
 */

@Configuration
public class PathLogAutoConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PathLogInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
