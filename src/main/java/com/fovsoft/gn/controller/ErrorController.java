package com.fovsoft.gn.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error22")
    public ModelAndView loginError(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationException exception = (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg", exception.getMessage());

        return modelAndView;
    }
}
