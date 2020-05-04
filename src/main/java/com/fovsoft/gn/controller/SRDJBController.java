package com.fovsoft.gn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 收入登记表
 */
@Controller
@RequestMapping(value = "/yw/sr")
public class SRDJBController {

    @RequestMapping(value = "/index")
    public String index() {
        return "srdjb/index";
    }

    @RequestMapping(value = "/add")
    public String incomeAdd() {
        return "srdjb/add";
    }

}
