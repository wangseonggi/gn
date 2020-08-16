package com.fovsoft.gn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@PropertySource(value="classpath:common.properties", encoding="UTF-8")
public class LoginController {

    @Value("${project.name}")
    private String projectName;
    @Value("${project.group}")
    private String projectGroup;
    @Value("${project.title1}")
    private String projectTitle1;
    @Value("${project.title2}")
    private String projectTitle2;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("name", projectName);
        modelAndView.addObject("group", projectGroup);
        modelAndView.addObject("title1", projectTitle1);
        modelAndView.addObject("title2", projectTitle2);
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
}
