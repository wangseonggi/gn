package com.fovsoft.gn.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@PropertySource(value="classpath:common.properties", encoding="UTF-8")
public class CommonParamInterceptor implements HandlerInterceptor {

    @Value("${project.name}")
    private String projectName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    public void  postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String f = request.getHeader("x-requested-with");

        /**
         * 过滤器，向页面请求中加入title参数
         */
        if(f == null) {
            modelAndView.getModel().put("title", projectName);
        }
    }
}
