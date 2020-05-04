package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单控制器
 *
 */
@Controller
public class SysMenuController {

    @RequestMapping(value = "/xt/menu")
    public String index() {
        return "system/menu/index";
    }
}
