package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.DmDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.service.AzdSerivce;
import com.fovsoft.gn.service.common.DmService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/dm")
public class DmRestController {

    @Autowired
    private DmService dmService;

    /**
     * 批量获取下拉列表的方法
     * @param param 下拉列表名数组
     * @return
     */
    @RequestMapping(value = "/getDmByNames", produces = "application/json;charset=UTF-8", method = RequestMethod.POST )
    public JsonResult getDmByNames(@RequestParam(value="param[]") List<String> param) {
        Map result = dmService.getDmByNames(param);
        return new JsonResult(result);
    }

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

    /**
     * 性别
     * @return
     */
    @RequestMapping("/xb")
    public JsonResult xb() {
        return new JsonResult(dmService.xb());
    }

    /**
     * 民族
     * @return
     */
    @RequestMapping("/mz")
    public JsonResult mz() {
        return new JsonResult(dmService.mz());
    }

    /**
     * 政治面貌
     * @return
     */
    @RequestMapping("/zzmm")
    public JsonResult zzmm() {
        return new JsonResult(dmService.zzmm());
    }

    /**
     * 文化程度
     * @return
     */
    @RequestMapping("/whcd")
    public JsonResult whcd() {
        return new JsonResult(dmService.whcd());
    }

    /**
     * 生活状态
     * @return
     */
    @RequestMapping("/shzt")
    public JsonResult shzt() {
        return new JsonResult(dmService.shzt());
    }

    /**
     * 人口类型
     * @return
     */
    @RequestMapping("/rklx")
    public JsonResult rklx() {
        return new JsonResult(dmService.rklx());
    }
    /**
     * 在读情况
     * @return
     */
    @RequestMapping("/zdqk")
    public JsonResult zdqk() {
        return new JsonResult(dmService.zdqk());
    }
    /**
     * 残疾等级
     * @return
     */
    @RequestMapping("/cjdj")
    public JsonResult cjdj() {
        return new JsonResult(dmService.cjdj());
    }
    /**
     * 就业情况
     * @return
     */
    @RequestMapping("/jyqk")
    public JsonResult jyqk() {
        return new JsonResult(dmService.jyqk());
    }

    /**
     * 稳定就业、创业地域
     * @return
     */
    @RequestMapping("/wdjycydy")
    public JsonResult wdjycydy() {
        return new JsonResult(dmService.wdjycydy());
    }

    /**
     * 养老保险类型
     * @return
     */
    @RequestMapping("/ylbxlx")
    public JsonResult ylbxlx() {
        return new JsonResult(dmService.ylbxlx());
    }
}