package com.fovsoft.gn.controller.rest;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.service.system.SystemMenuService;
import com.fovsoft.gn.service.system.SystemRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/xt/menu")
public class SystemMenuRestController {

    @Autowired
    private SystemMenuService systemMenuService;

    @RequestMapping(value = "/getList", produces = "application/json;charset=UTF-8")
    public Object list(Integer page, Integer limit, Integer pid, String mc, Integer zt) {
        PageInfo pageInfo = systemMenuService.list(page, limit, pid, mc, zt);

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
