package com.fovsoft.gn.controller.query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 贫困户家庭基本情况查询controller
 */
@Controller
@RequestMapping(value="/query/jt")
public class QueryController {

    @RequestMapping(value = "/index")
    public String index() {
        return "query/index";
    }


    @RequestMapping(value = "/edit")
    public String familyEdit() {
        return "query/edit";
    }


    @RequestMapping(value = "/cyAdd")
    public String memberAdd(Model model, int id) {
        if(id > 0) {
            model.addAttribute("id", id);
        }
        return "query/cy_add";
    }
}
