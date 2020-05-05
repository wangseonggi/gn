package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import com.fovsoft.gn.service.system.SystemUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/xt/user")
public class SystemUserRestController {

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping(value = "/getList", produces = "application/json;charset=UTF-8")
    public Object list(Integer page, Integer limit, String username) {
        PageInfo pageInfo = systemUserService.list(page, limit, username);

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

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addOrUpdate(@RequestBody SystemUserHolder systemUserHolders) {
        // 校验两次输入是否一致
        if(!systemUserHolders.getPassword().trim().equals("")) {
            if(!systemUserHolders.getPassword().trim().equals(systemUserHolders.getOpassword().trim())) {
                return new JsonResult(1, "保存失败！两次密码不一致。");
            }
            else {
                // 密码加密
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodeString = passwordEncoder.encode(systemUserHolders.getPassword().trim());
                systemUserHolders.setPassword(encodeString);
            }
        }
        int affectRow = systemUserService.update(systemUserHolders);
        return new JsonResult(affectRow);
    }

    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public JsonResult get(Integer id) {
        SystemUserDO systemUserDO = systemUserService.get(id);
        return new JsonResult(systemUserDO);
    }
}
