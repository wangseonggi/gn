package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String edit() {
        return "system/user/edit";
    }
}
