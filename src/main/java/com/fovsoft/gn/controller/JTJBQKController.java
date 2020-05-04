package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 贫困户家庭基本情况controller
 */
@Controller
@RequestMapping(value="/yw/jt")
public class JTJBQKController {

    @RequestMapping(value = "/index")
    public String index() {
        return "pkhjtjbqk/index";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "pkhjtjbqk/add";
    }

    @RequestMapping(value = "/edit")
    public String familyEdit() {
        return "pkhjtjbqk/edit";
    }


    @RequestMapping(value = "/cyAdd")
    public String memberAdd(Model model, int id) {
        if(id > 0) {
            model.addAttribute("id", id);
        }
        return "pkhjtjbqk/cy_add";
    }
}
