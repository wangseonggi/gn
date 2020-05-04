package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.Sr1DO;
import com.fovsoft.gn.entity.Sr3DO;
import com.fovsoft.gn.entity.Sr5DO;
import com.fovsoft.gn.entity.Sr9DO;
import com.fovsoft.gn.entity.holder.FidNFHolder;
import com.fovsoft.gn.service.SrdjbSerivce;
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
@RequestMapping("/yw/sr")
public class SrdjbRestController {

    @Autowired
    private SrdjbSerivce srdjbSerivce;

    @RequestMapping(value = "/addSr1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addSr1(@RequestBody Map<String, Object> map) {
        int id = srdjbSerivce.addSr1(map);
        return new JsonResult(Integer.valueOf(id));
    }

    @RequestMapping(value = "/addSr3", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addSr3(@RequestBody Map<String, Object> map) {
        int insertRowNum = srdjbSerivce.addSr3(map);
        return new JsonResult(insertRowNum);
    }

    @RequestMapping(value = "/addSr5", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addSr5(@RequestBody Map<String, Object> map) {
        int insertRowNum = srdjbSerivce.addSr5(map);
        return new JsonResult(insertRowNum);
    }

    @RequestMapping(value = "/addSr9", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addSr9(@RequestBody Map<String, Object> map) {
        int insertRowNum = srdjbSerivce.addSr9(map);
        return new JsonResult(insertRowNum);
    }

    @RequestMapping(value = "/getSr1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getSr1(@RequestBody Sr1DO sr1DO) {
        List list = srdjbSerivce.getSr1(sr1DO);
        Map data = new HashMap();
        for (Object item :
                list) {
            data.putAll((Map) item);
        }
        return new JsonResult(data);
    }

    @RequestMapping(value = "/getSr3", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getSr3(@RequestBody FidNFHolder fidNFHolder) {
        List<Sr3DO> list = srdjbSerivce.getSr3(fidNFHolder);


        List listRy = new ArrayList();
        Map mapQt = new HashMap();
        for (Sr3DO obj :
                list) {
            if (obj.getXh() == 0) {
                mapQt.put(obj.getLb() + "_item1", obj.getItem1());
                mapQt.put(obj.getLb() + "_item2", obj.getItem2());
                mapQt.put(obj.getLb() + "_item3", obj.getItem3());
                mapQt.put(obj.getLb() + "_item4", obj.getItem4());
                mapQt.put(obj.getLb() + "_item5", obj.getItem5());
                mapQt.put(obj.getLb() + "_item6", obj.getItem6());
                mapQt.put(obj.getLb() + "_item7", obj.getItem7());
                mapQt.put(obj.getLb() + "_item8", obj.getItem8());
                mapQt.put(obj.getLb() + "_item9", obj.getItem9());
                mapQt.put(obj.getLb() + "_item10", obj.getItem10());
                mapQt.put(obj.getLb() + "_item11", obj.getItem11());
                mapQt.put(obj.getLb() + "_item12", obj.getItem12());
            } else {
                Map t = new HashMap();
                t.put("xm", obj.getXm());
                t.put("wggz", obj.getWggz());
                t.put("wgdz", obj.getWgdz());
                t.put("wgqymc", obj.getWgqymc());
                t.put("wgljsj", obj.getWgljsj());
                t.put("item1", obj.getItem1());
                t.put("item2", obj.getItem2());
                t.put("item3", obj.getItem3());
                t.put("item4", obj.getItem4());
                t.put("item5", obj.getItem5());
                t.put("item6", obj.getItem6());
                t.put("item7", obj.getItem7());
                t.put("item8", obj.getItem8());
                t.put("item9", obj.getItem9());
                t.put("item10", obj.getItem10());
                t.put("item11", obj.getItem11());
                t.put("item12", obj.getItem12());
                listRy.add(t);
            }
        }

        Map resultMap = new HashMap();
        resultMap.put("ry", listRy);
        resultMap.put("qt", mapQt);
        return new JsonResult(resultMap);
    }

    @RequestMapping(value = "/getSr5", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getSr5(@RequestBody FidNFHolder fidNFHolder) {
        List<Sr5DO> list = srdjbSerivce.getSr5(fidNFHolder);
        Map map = new HashMap();
        for (Sr5DO obj : list) {
            map.put(obj.getLb() + "_item1", obj.getItem1());
            map.put(obj.getLb() + "_item2", obj.getItem2());
            map.put(obj.getLb() + "_item3", obj.getItem3());
            map.put(obj.getLb() + "_item4", obj.getItem4());
            map.put(obj.getLb() + "_item5", obj.getItem5());
            map.put(obj.getLb() + "_item6", obj.getItem6());
            map.put(obj.getLb() + "_item7", obj.getItem7());
            map.put(obj.getLb() + "_item8", obj.getItem8());
            map.put(obj.getLb() + "_item9", obj.getItem9());
            map.put(obj.getLb() + "_item10", obj.getItem10());
            map.put(obj.getLb() + "_item11", obj.getItem11());
            map.put(obj.getLb() + "_item12", obj.getItem12());
        }

        Map result = new HashMap();
        result.put("d", map);

        Map jtwdzsr = srdjbSerivce.getJTWDZSR(fidNFHolder);
        result.put("jtwdzsr", jtwdzsr.get("total"));

        Map jtzzc = srdjbSerivce.getJTZZC(fidNFHolder);
        result.put("jtzzc", jtzzc.get("total"));

        return new JsonResult(result);
    }

    @RequestMapping(value = "/getSr9", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult getSr9(@RequestBody FidNFHolder fidNFHolder) {
        List<Sr9DO> list = srdjbSerivce.getSr9(fidNFHolder);
        Map map = new HashMap();
        for (Sr9DO obj : list) {
            map.put(obj.getLb() + "_item1", obj.getItem1());
            map.put(obj.getLb() + "_item2", obj.getItem2());
            map.put(obj.getLb() + "_item3", obj.getItem3());
            map.put(obj.getLb() + "_item4", obj.getItem4());
            map.put(obj.getLb() + "_item5", obj.getItem5());
            map.put(obj.getLb() + "_item6", obj.getItem6());
            map.put(obj.getLb() + "_item7", obj.getItem7());
            map.put(obj.getLb() + "_item8", obj.getItem8());
            map.put(obj.getLb() + "_item9", obj.getItem9());
            map.put(obj.getLb() + "_item10", obj.getItem10());
            map.put(obj.getLb() + "_item11", obj.getItem11());
            map.put(obj.getLb() + "_item12", obj.getItem12());
        }
        return new JsonResult(map);
    }
}