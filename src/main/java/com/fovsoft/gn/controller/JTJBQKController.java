package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 家庭
 *
 *
 */
@Controller
@RequestMapping(value="/yw/jt")
public class JTJBQKController {

    @RequestMapping(value = "/index")
    public String index() {
        return "jt/index";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "jt/add";
    }

    @RequestMapping(value = "/edit")
    public String edit() {
        return "jt/edit";
    }


    @RequestMapping(value = "/cyAdd")
    public String cyAdd(Model model, Integer id) {
        if(id != null && id > 0) {
            model.addAttribute("id", id);
        }
        return "jt/cyAdd";
    }

//    @RequestMapping(value = "/cyAdd")
//    public String memberAdd(Model model, int id) {
//        if(id > 0) {
//            model.addAttribute("id", id);
//        }
//        return "jt/cy_add";
//    }
}
