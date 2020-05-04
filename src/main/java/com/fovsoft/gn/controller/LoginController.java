package com.fovsoft.gn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class LoginController {

//    @RequestMapping(value = "/login")
//    public String login(String error) {
//        log.info("请求的error:" + error);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth instanceof AnonymousAuthenticationToken){
//            return "login";
//        }
//        else {
//            return  "redirect:/index";
//        }
//    }
    @RequestMapping(value = "/login")
    public ModelAndView login(String error) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

//    @RequestMapping(value = "/error")
//    public String error() {
//        return "error";
//    }

}
