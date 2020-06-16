package com.fovsoft.gn.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: by tpc
 * @date: 2019/10/31 15:24
 * @description: 用户过期配置
 **/
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    //获取jackjson map对象
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        //封装登录认证成功的json信息
        Map<String,Object> map=new HashMap<>();

        event.getResponse().setContentType("application/json:charset=utf-8");
        map.put("code",0);
        map.put("msg","已经另一台机器登录，您被迫下线。");
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(map));
        event.getResponse().getWriter().flush();
        event.getResponse().getWriter().close();
        event.getResponse().sendRedirect("/login");

    }
}
