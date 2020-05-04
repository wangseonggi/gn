package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/foo")
    public JsonResult foo() {
        float s = 1/0;
        return new JsonResult(s);
    }
}
