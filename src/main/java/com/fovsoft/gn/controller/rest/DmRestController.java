package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.DmDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.service.AzdSerivce;
import com.fovsoft.gn.service.common.DmService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/dm")
public class DmRestController {

    @Autowired
    private DmService dmService;

    /**
     * 返贫原因
     * @return
     */
    @RequestMapping("/fpyy")
    public JsonResult fpyy() {
        return new JsonResult(dmService.fpyy());
    }

    /**
     * 健康状况
     * @return
     */
    @RequestMapping("/jkzk")
    public JsonResult jkzk() {
        return new JsonResult(dmService.fpyy());
    }

    /**
     * 劳动技能
     * @return
     */
    @RequestMapping("/ldjn")
    public JsonResult ldjn() {
        return new JsonResult(dmService.ldjn());
    }

    /**
     * 贫困户属性
     * @return
     */
    @RequestMapping("/pkhsx")
    public JsonResult pkhsx() {
        return new JsonResult(dmService.pkhsx());
    }

    /**
     * 入户路类型
     * @return
     */
    @RequestMapping("/rhllx")
    public JsonResult rhllx() {
        return new JsonResult(dmService.rhllx());
    }

    /**
     * 失学或辍学原因
     * @return
     */
    @RequestMapping("/sxhcxyy")
    public JsonResult sxhcxyy() {
        return new JsonResult(dmService.sxhcxyy());
    }

    /**
     * 危房级别
     * @return
     */
    @RequestMapping("/wfjb")
    public JsonResult wfjb() {
        return new JsonResult(dmService.wfjb());
    }

    /**
     * 与户主关系
     * @return
     */
    @RequestMapping("/yhzgx")
    public JsonResult yhzgx() {
        return new JsonResult(dmService.yhzgx());
    }

    /**
     * 在校生情况
     * @return
     */
    @RequestMapping("/zxsqk")
    public JsonResult zxsqk() {
        return new JsonResult(dmService.zxsqk());
    }

    /**
     * 主要燃料类型
     * @return
     */
    @RequestMapping("/zyrllx")
    public JsonResult zyrllx() {
        return new JsonResult(dmService.zyrllx());
    }
}