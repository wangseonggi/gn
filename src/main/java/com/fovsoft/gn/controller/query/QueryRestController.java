package com.fovsoft.gn.controller.query;

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
@RequestMapping("/query/jt")
public class QueryRestController {
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





    @RequestMapping(value = "/getCyList",  produces = "application/json;charset=UTF-8")
    public JsonResult getMemberList(Integer fid) {
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






    @RequestMapping(value = "/getZpyy",  produces = "application/json;charset=UTF-8")
    public JsonResult getAddiction(Integer fid) {
        ZpyyDO zpyyDO = serivce.getAddiction(fid);
        return new JsonResult(zpyyDO);
    }

    @RequestMapping(value = "/getScshtj",  produces = "application/json;charset=UTF-8")
    public JsonResult getCondition(Integer fid) {
        ScshtjDO scshtjDO = serivce.getCondition(fid);
        return new JsonResult(scshtjDO);
    }

    @RequestMapping(value = "/getNew",  produces = "application/json;charset=UTF-8")
    public JsonResult getNew(Integer id) {
        JtjbxxDO jtjbxxDO = serivce.getNew(id);
        return new JsonResult(jtjbxxDO);
    }

    /**
     * 更具家庭id查询检查户主是否存在
     * @param id 家庭id
     * @return
     */
    @RequestMapping(value = "/getHz", produces = "application/json;charset=UTF-8")
    public JsonResult getHz(Integer id) {
        int num = serivce.getHz(id);
        return new JsonResult(num);
    }
}
