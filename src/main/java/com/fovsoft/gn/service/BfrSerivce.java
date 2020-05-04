package com.fovsoft.gn.service;


import com.fovsoft.gn.entity.BfrDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.entity.holder.BfrBqhHolder;
import com.fovsoft.gn.mapper.bfr.BfrMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收入登记表service
 *
 *
 */
@Service
public class BfrSerivce {

    @Resource
    private BfrMapper bfrMapper;


    public PageInfo getList(Integer page, Integer limit, String name, String gzdw, String lxdh){
        PageHelper.startPage(page, limit);
        List<BfrBqhHolder> list = bfrMapper.list(name, gzdw, lxdh);

        return new PageInfo(list);
    }

    public BfrDo get(int id) {
        return bfrMapper.get(id);
    }

    public int addOrUpdate(BfrDo bfrDo) {
        int id = 0;
        if(bfrDo.getId() == 0) { // 新增
            id = bfrMapper.add(bfrDo);
        }
        else {                  // 更新
            id = bfrMapper.update(bfrDo);
        }

        return id;
    }


    public List<BfgxXXHolder> getBBfrXX(int id) {
        return bfrMapper.getBBfrxx(id);
    }

    public BfgxXXHolder getBqh(int id) {
        return bfrMapper.getBqh(id);
    }

    public int zd(int bid, int[] fids) {
        bfrMapper.deleteZd(bid);

        int rows = bfrMapper.zd(bid, fids);

        return rows;
    }
}
