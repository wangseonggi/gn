package com.fovsoft.gn.controller.rest;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.service.system.SystemRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/xt/role")
public class SystemRoleRestController {

    @Autowired
    private SystemRoleService systemRoleService;

    @RequestMapping(value = "/getList",  produces = "application/json;charset=UTF-8")
    public Object list(Integer page, Integer limit, String mc, String sm) {
        PageInfo pageInfo = systemRoleService.list(page, limit, mc, sm);

        Map result = new HashMap();
        result.put("data", pageInfo.getList());
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);

        return result;
    }

    @RequestMapping("/del")
    public int del(Integer id) {

        return 0;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int update(SystemUserDO systemUserDO) {

        return 0;
    }

    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public SystemUserDO get(Integer id) {

        return null;
    }
}
