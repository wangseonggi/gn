package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制器
 *
 */
@Controller
public class SysRoleController {
    @RequestMapping(value = "/xt/role")
    public String index() {
        return "system/role/index";
    }
}
