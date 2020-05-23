package com.fovsoft.gn.controller;

import com.fovsoft.gn.security.component.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理控制器
 *
 */
@Controller
public class SysUserController {

    @RequestMapping(value = "/xt/user")
    public String index() {
        return "system/user/index";
    }

    @RequestMapping(value = "/xt/user/edit")
    public Object edit(Integer isProfile) {
        // 处理个人信息
        if(isProfile != null && isProfile == 1) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUser customUser = (CustomUser)auth.getPrincipal();
            ModelMap map = new ModelMap();
            map.put("user", customUser);
            return new ModelAndView("system/user/edit", map);
        }
        else {
            return "system/user/edit";
        }
    }
}
