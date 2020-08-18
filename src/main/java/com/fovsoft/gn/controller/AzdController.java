package com.fovsoft.gn.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 安置点
 */
@Controller
@RequestMapping(value = "/yw/azd")
public class AzdController {


    @RequestMapping(value = "/index")
    public String index() {
        return "azd/index";
    }

    @RequestMapping(value = "/add")
    public String addAzd(Model model, Integer id) {
        model.addAttribute("id", id);
        return "azd/add";
    }

    @RequestMapping(value = "/fwxx")
    public String detail() {
        return "azd/fwxx";
    }

    @RequestMapping(value = "/addFwxx")
    public String addFwxx() {
        return "azd/fwxxAdd";
    }

    @RequestMapping(value = "/glzh")
    public String gxzh() {
        return "azd/glzh";
    }

}
