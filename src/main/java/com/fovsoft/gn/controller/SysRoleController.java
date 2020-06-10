package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制器
 *
 */
@Controller
@RequestMapping(value = "/xt/role")
public class SysRoleController {
    @RequestMapping(value = "/index")
    public String index() {
        return "system/role/index";
    }

    @RequestMapping(value = "/grant")
    public String grant(Model model, Integer id) {
        model.addAttribute("id", id);

        return "system/role/grant";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "system/role/add";
    }
}
