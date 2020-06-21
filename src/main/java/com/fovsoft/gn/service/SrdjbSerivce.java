package com.fovsoft.gn.service;


import com.fovsoft.gn.entity.Sr1DO;
import com.fovsoft.gn.entity.holder.FidNFHolder;
import com.fovsoft.gn.entity.holder.JbxxSrHolder;
import com.fovsoft.gn.mapper.srdjb.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 收入登记表service
 *
 *
 */
@Service
public class SrdjbSerivce {

    @Resource
    private SRDJBMapper srdjbMapper;

    @Resource
    private Sr1Mapper sr1Mapper;

    @Resource
    private Sr3Mapper sr3Mapper;

    @Resource
    private Sr5Mapper sr5Mapper;

    @Resource
    private Sr9Mapper sr9Mapper;

    public PageInfo getList(Integer page, Integer limit, String name, String sfzhm) {
        PageHelper.startPage(page, limit);

        String name1 = name != null && !name.equals("") ? name : null;
        String sfzhm1 = sfzhm != null && !sfzhm.equals("") ? sfzhm : null;

        List<JbxxSrHolder> list =  srdjbMapper.list(name1, sfzhm1);
        return new PageInfo(list);
    }

    public int addSr1(Map<String, Object> map) {
        int fid = Integer.parseInt(map.get("fid").toString());
        String year = map.get("year").toString();

        sr1Mapper.delete(fid, year);

        // 拼接成 {'01_01_01':{'item1':'111', {'item2':'222'} ... }} 的形式
        Map<String, Object> lbItemMap = new HashMap();

        HashMap<String, Object> data = (HashMap) map.get("data");

        for(Map.Entry<String, Object> entry : data.entrySet()) {
            String keyPrefix = entry.getKey().substring(0, 8);
            String keyItem = entry.getKey().substring(9);
            String val = entry.getValue().toString();
            if(lbItemMap.containsKey(keyPrefix)) {
                ((Map)lbItemMap.get(keyPrefix)).put(keyItem, val);
            }
            else {
                Map tt = new HashMap();
                tt.put(keyItem, val);
                lbItemMap.put(keyPrefix, tt);
            }
        }

        List insertList = new ArrayList();
        for(Map.Entry<String, Object> entry : lbItemMap.entrySet()) {
            Map ttt = new HashMap();
            String lbs = entry.getKey();
            String[] lbsArr = lbs.split("_");
            ttt.put("fid", fid);
            ttt.put("nf", year);
            ttt.put("lb1", lbsArr[0]);
            ttt.put("lb2", lbsArr[1]);
            ttt.put("lb3", lbsArr[2]);
            ttt.putAll((Map)entry.getValue());
            insertList.add(ttt);
        }

        int affectRow = sr1Mapper.add(insertList);
        return affectRow;
    }

    public int addSr3(Map<String, Object> map) {
        int fid = Integer.parseInt(map.get("fid").toString());
        String year = map.get("year").toString();

        sr3Mapper.delete(fid, year);

        int affectRowRy = sr3Mapper.addRy((List) map.get("list"));

        Map<String, Object> lbItemMap = new HashMap();
        Map<String, Object> field = (Map<String, Object>)map.get("field");
        for(Map.Entry<String, Object> entry : field.entrySet()) {
            String keyPrefix = entry.getKey().substring(0, 2);
            String keyItem = entry.getKey().substring(3);
            String val = entry.getValue().toString();
            if(lbItemMap.containsKey(keyPrefix)) {
                ((Map)lbItemMap.get(keyPrefix)).put(keyItem, val);
            }
            else {
                Map tt = new HashMap();
                tt.put(keyItem, val);
                lbItemMap.put(keyPrefix, tt);
            }
        }

        List insertList = new ArrayList();
        for(Map.Entry<String, Object> entry : lbItemMap.entrySet()) {
            Map ttt = new HashMap();
            String lb = entry.getKey();
            ttt.put("fid", fid);
            ttt.put("nf", year);
            ttt.put("lb", lb);
            ttt.putAll((Map)entry.getValue());
            insertList.add(ttt);
        }
        int affectRowQt = sr3Mapper.addQt(insertList);
        return affectRowRy + affectRowQt;
    }

    public int addSr5(Map<String, Object> map) {
        return sr5Mapper.add(deal(map, sr5Mapper));
    }
    public int addSr9(Map<String, Object> map) {
        return sr9Mapper.add(deal(map, sr9Mapper));
    }


    public List getSr1(Sr1DO sr1DO){
        Map<String, Object> data = new HashMap();
        data.put("fid", sr1DO.getFid());
        data.put("nf", sr1DO.getNf());
        List<Sr1DO> list = sr1Mapper.get(data);

        List ret = new ArrayList();
        for (Sr1DO obj: list) {
            Map tmp = new HashMap();
            String prefix = obj.getLb1() + "_" + obj.getLb2() + "_" + obj.getLb3();
            tmp.put(prefix + "_item1", obj.getItem1());
            tmp.put(prefix + "_item2", obj.getItem2());
            tmp.put(prefix + "_item3", obj.getItem3());
            tmp.put(prefix + "_item4", obj.getItem4());
            tmp.put(prefix + "_item5", obj.getItem5());
            tmp.put(prefix + "_item6", obj.getItem6());
            tmp.put(prefix + "_item7", obj.getItem7());
            tmp.put(prefix + "_item8", obj.getItem8());
            tmp.put(prefix + "_item9", obj.getItem9());
            tmp.put(prefix + "_item10", obj.getItem10());
            tmp.put(prefix + "_item11", obj.getItem11());
            tmp.put(prefix + "_item12", obj.getItem12());
            ret.add(tmp);
        }

        return ret;
    }

    public List getSr3(FidNFHolder fidNFHolder) {
        Map<String, Object> param = new HashMap();
        param.put("fid", fidNFHolder.getFid());
        param.put("nf", fidNFHolder.getNf());

        List list =  sr3Mapper.get(param);
        return list;
    }

    public List getSr5(FidNFHolder fidNFHolder) {
        Map<String, Object> param = new HashMap();
        param.put("fid", fidNFHolder.getFid());
        param.put("nf", fidNFHolder.getNf());
        return sr5Mapper.get(param);
    }

    public List getSr9(FidNFHolder fidNFHolder) {
        Map<String, Object> param = new HashMap();
        param.put("fid", fidNFHolder.getFid());
        param.put("nf", fidNFHolder.getNf());
        return sr9Mapper.get(param);
    }

    private List deal(Map<String, Object> map, CommonMapper mapper) {
        int fid = Integer.parseInt(map.get("fid").toString());
        String year = map.get("year").toString();

        mapper.delete(fid, year);

        Map<String, Object> lbItemMap = new HashMap();
        HashMap<String, Object> data = (HashMap) map.get("data");

        for(Map.Entry<String, Object> entry : data.entrySet()) {
            String keyPrefix = entry.getKey().substring(0, 2);
            String keyItem = entry.getKey().substring(3);
            String val = entry.getValue().toString();
            if(lbItemMap.containsKey(keyPrefix)) {
                ((Map)lbItemMap.get(keyPrefix)).put(keyItem, val);
            }
            else {
                Map tt = new HashMap();
                tt.put(keyItem, val);
                lbItemMap.put(keyPrefix, tt);
            }
        }

        List insertList = new ArrayList();
        for(Map.Entry<String, Object> entry : lbItemMap.entrySet()) {
            Map ttt = new HashMap();
            String lbs = entry.getKey();
            String[] lbsArr = lbs.split("_");
            ttt.put("fid", fid);
            ttt.put("nf", year);
            ttt.put("lb", lbsArr[0]);
            ttt.putAll((Map)entry.getValue());
            insertList.add(ttt);
        }

        return insertList;
    }

    // 家庭稳定总收入
    public Map getJTWDZSR(FidNFHolder fidNFHolder) {
        Map<String, Object> param = new HashMap();
        param.put("fid", fidNFHolder.getFid());
        param.put("nf", fidNFHolder.getNf());
        Map<String,Float> result = sr5Mapper.getJTWDZSR(param);
        return result;
    }

    // 家庭总支出
    public Map getJTZZC(FidNFHolder fidNFHolder) {
        Map<String, Object> param = new HashMap();
        param.put("fid", fidNFHolder.getFid());
        param.put("nf", fidNFHolder.getNf());
        Map<String,Float> result = sr5Mapper.getJTZZC(param);
        return result;
    }
}
