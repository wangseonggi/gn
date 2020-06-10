package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 帮扶人
 */
@Controller
@RequestMapping(value = "/yw/bfr")
public class BfrController {
    @RequestMapping(value = "/index")
    public String index() {
        return "bfr/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model, Integer id) {
        model.addAttribute("id", id);
        return "bfr/add";
    }

    @RequestMapping(value = "/bfgx")
    public String bfgx(Model model, Integer id) {
        model.addAttribute("id", id);
        return "bfr/bfgx";
    }
}
