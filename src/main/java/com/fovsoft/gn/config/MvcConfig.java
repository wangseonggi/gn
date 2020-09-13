package com.fovsoft.gn.config;

import com.fovsoft.gn.interceptor.CommonParamInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public CommonParamInterceptor getD(){
        return new CommonParamInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getD());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/assets/**", "/error");
    }
}
