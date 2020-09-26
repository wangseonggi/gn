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

    @RequestMapping(value = "/photo")
    public String photo(Model model, Integer id, String type) {
        model.addAttribute("id", id);
        model.addAttribute("type", type);

        return "jt/photo";
    }

    @RequestMapping(value = "/uploadImg")
    public String uploadImg(Model model, Integer fid) {
        model.addAttribute("fid", fid);
        return "jt/uploadImg";
    }
}
