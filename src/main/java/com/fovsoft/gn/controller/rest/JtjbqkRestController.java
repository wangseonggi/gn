package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.JtcyDO;
import com.fovsoft.gn.entity.JtjbxxDO;
import com.fovsoft.gn.entity.ScshtjDO;
import com.fovsoft.gn.entity.ZpyyDO;
import com.fovsoft.gn.service.JTJBQKSerivce;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yw/jt")
public class JtjbqkRestController {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    JTJBQKSerivce serivce;

    @RequestMapping(value = "/getList")
    public Object index(Integer page, Integer limit, String name, String sfzhm) {
        logger.info(page + " " + limit);
        PageInfo pageInfo = serivce.getList(page, limit, name, sfzhm);

        Map result = new HashMap();
        result.put("data", pageInfo.getList());
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult add(@RequestBody JtjbxxDO jtjbxxDO) {
        int id = serivce.addOrUpdateFamilyBase(jtjbxxDO);
        return new JsonResult(Integer.valueOf(id));
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public JsonResult del(Integer id) {
        int returnId = serivce.del(id);

        return new JsonResult(Integer.valueOf(returnId));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JsonResult get(Integer id) {
        JtjbxxDO jtjbxxDO = serivce.get(id);
        return new JsonResult(jtjbxxDO);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addZpyy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addAddition(@RequestBody ZpyyDO zpyyDO) {
        int id = serivce.addOrUpdateFamilyBaseAddition(zpyyDO);
        return new JsonResult(Integer.valueOf(id));
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addScshtj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addCondition(@RequestBody ScshtjDO scshtjDO) {
        int id = serivce.addOrUpdateFamilyBaseCondition(scshtjDO);
        return new JsonResult(Integer.valueOf(id));
    }

    @RequestMapping(value = "/getCyList",  produces = "application/json;charset=UTF-8")
    public JsonResult getMemberList(int fid) {
        List<JtcyDO> list = serivce.getMemberList(fid);

        return new JsonResult(list);
    }

    @RequestMapping(value = "/getCy", produces = "application/json;charset=UTF-8")
    public JsonResult getMember(Integer id) {
        JtcyDO member = serivce.getMember(id);
        return new JsonResult(member);
    }

    @RequestMapping(value = "/addCy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addMember(@RequestBody JtcyDO jtcyDO) {
        int id = serivce.addOrUpdateFamilyBaseMember(jtcyDO);
        return new JsonResult(Integer.valueOf(id));
    }

    @RequestMapping(value = "/updateCy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateMember(@RequestBody JtcyDO jtcyDO) {
        int id = serivce.addOrUpdateFamilyBaseMember(jtcyDO);
        return new JsonResult(Integer.valueOf(id));
    }

    @RequestMapping(value = "/delCy", method = RequestMethod.GET)
    public JsonResult delMember(Integer id) {
        int returnId = serivce.delMember(id);
        return new JsonResult(Integer.valueOf(returnId));
    }

    /**
     * 批量删除
     *
     * 多表批量删除，没加事务
     *
     * @return
     */
    @RequestMapping(value = "/delAll",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult delAll(@RequestBody ArrayList<Integer> ids) {
        String inStr = "";
        for (int i:
        ids) {
            inStr += i + ",";
        }
        inStr = inStr.substring(0, inStr.length() - 1); logger.info(inStr);
        serivce.delAll(inStr);
        return new JsonResult();
    }

    @RequestMapping(value = "/getZpyy",  produces = "application/json;charset=UTF-8")
    public JsonResult getAddiction(int fid) {
        ZpyyDO zpyyDO = serivce.getAddiction(fid);
        return new JsonResult(zpyyDO);
    }

    @RequestMapping(value = "/getScshtj",  produces = "application/json;charset=UTF-8")
    public JsonResult getCondition(int fid) {
        ScshtjDO scshtjDO = serivce.getCondition(fid);
        return new JsonResult(scshtjDO);
    }

    @RequestMapping(value = "/getNew",  produces = "application/json;charset=UTF-8")
    public JsonResult getNew(int id) {
        JtjbxxDO jtjbxxDO = serivce.getNew(id);
        return new JsonResult(jtjbxxDO);
    }

    /**
     * 更具家庭id查询检查户主是否存在
     * @param id 家庭id
     * @return
     */
    @RequestMapping(value = "/getHz", produces = "application/json;charset=UTF-8")
    public JsonResult getHz(int id) {
        int num = serivce.getHz(id);
        return new JsonResult(num);
    }
}
