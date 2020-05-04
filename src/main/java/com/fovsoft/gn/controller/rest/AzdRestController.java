package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.service.AzdSerivce;
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
@RequestMapping("/yw/azd")
public class AzdRestController {

    @Autowired
    private AzdSerivce azdSerivce;

    @RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List getList() {
        return azdSerivce.getList();
    }

    @RequestMapping(value = "/getFwxxList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object getFwxxList(Integer page, Integer limit, Integer aid, String ld ,String dy ,String fh ,Double mjgt ,Double mjlt) {

        if(ld != null) {
            ld = ld.equals("") ? null : ld;
        }
        if(dy != null) {
            dy = dy.equals("") ? null : dy;
        }
        if(fh != null) {
            fh = fh.equals("") ? null : fh;
        }

        PageInfo pageInfo = azdSerivce.getFwxxList(page, limit, aid, ld, dy, fh, mjgt, mjlt);
        Map result = new HashMap();
        result.put("data", pageInfo.getList());
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);

        return result;
    }

    @RequestMapping(value = "/fwxxGet",  produces = "application/json;charset=UTF-8")
    public JsonResult fwxxGet(int id) {
        AzdFwxxDo azdFwxxDo = azdSerivce.fwxxGet(id);
        return new JsonResult(azdFwxxDo);
    }

    @RequestMapping(value = "/fwxxAdd",  produces = "application/json;charset=UTF-8")
    public JsonResult fwxxAdd(@RequestBody AzdFwxxDo azdFwxxDo) {
        int affectRow = azdSerivce.fwxxAddOrUpdate(azdFwxxDo);
        return new JsonResult(affectRow);
    }


    /**
     * 获取关联住户信息
     *
     * 该方法根据住房id返回当前住房的住户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGLZHXX",  produces = "application/json;charset=UTF-8")
    public JsonResult getGLZHXX(int id) {
        BfgxXXHolder bfgxXXHolder = azdSerivce.getGLZHXX(id);
        return new JsonResult(bfgxXXHolder);
    }
}