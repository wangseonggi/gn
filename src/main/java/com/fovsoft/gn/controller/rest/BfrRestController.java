package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.*;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.entity.holder.FidNFHolder;
import com.fovsoft.gn.service.BfrSerivce;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yw/bfr")
public class BfrRestController {

    @Autowired
    private BfrSerivce bfrSerivce;

    /**
     * 帮扶人列表
     * @param page
     * @param limit
     * @param name
     * @param gzdw
     * @param lxdh
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object getList(Integer page, Integer limit, String name, String gzdw, String lxdh) {
        PageInfo pageInfo = bfrSerivce.getList(page, limit, name, gzdw, lxdh);

        Map result = new HashMap();
        result.put("data", pageInfo.getList());
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);

        return result;
    }

    /**
     * 获取单个帮扶人信息
     * @param bfrid
     * @return
     */
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public JsonResult<BfrDo> get(int bfrid) {
        BfrDo bfrDo = bfrSerivce.get(bfrid);
        return new JsonResult(bfrDo);
    }

    /**
     * 新增或更新帮扶人
     * @param bfrDo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult add(@RequestBody BfrDo bfrDo) {
        int affectRow = bfrSerivce.addOrUpdate(bfrDo);
        return new JsonResult(affectRow);
    }

    /**
     * 根据帮扶人id查询帮扶对象
     * @param bfrid
     * @return
     */
    @RequestMapping(value = "getBBfrXX", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getBBfrXX(Integer bfrid) {
        List<BfgxXXHolder> list = bfrSerivce.getBBfrXX(bfrid);
        return new JsonResult(list);
    }

    /**
     * 根据搬迁家庭ID查询 户主姓名，身份证号，家庭人口及贫困户属性
     */
    @RequestMapping(value = "getBqh", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getBqh(int jtid) {
        BfgxXXHolder bfgxXXHolder = bfrSerivce.getBqh(jtid);
        return new JsonResult(bfgxXXHolder);
    }

    /**
     * 指定被帮扶家庭
     * @return
     */
    @RequestMapping(value = "zd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult zd(@RequestParam(value = "bfrid")int bfrid,@RequestParam(value = "bqhids[]")int[] bqhids) {
        int affectRow = bfrSerivce.zd(bfrid, bqhids);
        return new JsonResult(affectRow);
    }
}