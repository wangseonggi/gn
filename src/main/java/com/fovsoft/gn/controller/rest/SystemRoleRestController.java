package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.SystemRoleDO;
import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.Menu;
import com.fovsoft.gn.entity.holder.MenuData;
import com.fovsoft.gn.service.system.SystemRoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/xt/role")
public class SystemRoleRestController {

    @Autowired
    private SystemRoleService systemRoleService;

    @RequestMapping(value = "/index",  produces = "application/json;charset=UTF-8")
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
    public JsonResult del(Integer id) {
        int num = systemRoleService.del(id);
        return new JsonResult(num);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int update(SystemUserDO systemUserDO) {

        return 0;
    }

    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public SystemUserDO get(Integer id) {

        return null;
    }

    @RequestMapping(value = "/getMenuTree", produces = "application/json;charset=UTF-8")
    public JsonResult getMenuTree(Integer roleId) {
        List<Menu> tree = systemRoleService.getMenuTree(roleId);
        return new JsonResult(tree);
    }

    @RequestMapping(value = "/getMenuData", produces = "application/json;charset=UTF-8")
    public JsonResult getMenuData(Integer roleId) {
        Map menuData = systemRoleService.getMenuData(roleId);
        return new JsonResult(menuData);
    }

    @RequestMapping(value = "/grant", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult grant(@RequestBody MenuData menuData) {
        log.info(menuData.toString());

        systemRoleService.grant(menuData);

        return new JsonResult();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult add(@RequestBody SystemRoleDO systemRoleDO) {
        systemRoleDO.setMc("ROLE_" + systemRoleDO.getMc());
        systemRoleDO.setCjsj(new Date());
        int result = systemRoleService.add(systemRoleDO);
        return new JsonResult(result);
    }
}
