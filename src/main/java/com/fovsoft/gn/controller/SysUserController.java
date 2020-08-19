package com.fovsoft.gn.controller;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.FrameworkUserHolder;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import com.fovsoft.gn.security.component.CustomUser;
import com.fovsoft.gn.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 用户管理控制器
 *
 */
@Controller
@RequestMapping(value = "/xt/user")
public class SysUserController {

    @Autowired
    private SystemUserService systemUserService;


    @RequestMapping(value = "/index")
    public String index() {
        return "system/user/index";
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(Integer id) {
        // 处理个人信息
        if(id == null) {    // 新增页
            return new ModelAndView("system/user/add");
        }
        else if(id == 0) {  // 右上角进入的编辑页
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUser customUser = (CustomUser)auth.getPrincipal();
            ModelMap map = new ModelMap();
            map.put("user", customUser);
            map.put("isProfile", 1);
            map.put("aid", 1);
            return new ModelAndView("system/user/add", map);
        }
        else {  // 列表进入的编辑页
            FrameworkUserHolder customUser = new FrameworkUserHolder();
            customUser.setId(id);
            ModelMap map = new ModelMap();
            map.put("user", customUser);
            return new ModelAndView("system/user/add", map);
        }
    }

    @RequestMapping(value = "/setRole")
    public Object setRole(Model model, Integer id) {
        model.addAttribute("userid", id);
        return "system/user/setRole";
    }
}
