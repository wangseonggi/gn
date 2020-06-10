package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单控制器
 *
 */
@Controller
@RequestMapping(value = "/xt/menu")
public class SysMenuController {

    @RequestMapping(value = "/index")
    public String index() {
        return "system/menu/index";
    }
}
